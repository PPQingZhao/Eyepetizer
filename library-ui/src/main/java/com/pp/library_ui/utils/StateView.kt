package com.pp.library_ui.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import com.pp.library_ui.BR
import com.pp.library_ui.R

class StateView {

    private var contentParent: ViewGroup
    private var contentLayoutParams: ViewGroup.LayoutParams
    private var mContentView: View
    private var loadingView: View? = null
    private var errorView: View? = null
    private var emptyView: View? = null

    private var curViewState: ViewState? = null
    private val indexOfContentView: Int

    private constructor(contentView: View, loadingView: View?, errorView: View?, emptyView: View?) {
        this.mContentView = contentView

        this.loadingView = loadingView
        this.errorView = errorView
        this.emptyView = emptyView

        this.contentLayoutParams = contentView.layoutParams
        this.contentParent = contentView.parent as ViewGroup
        this.indexOfContentView = this.contentParent.indexOfChild(contentView)
        this.curViewState = ViewState.Content(contentView)
    }

    private fun showStateView(target: ViewState) {
        if (target.equals(curViewState)) {
            Log.i("StateView", "target: ${target::javaClass} is showing")
            return
        }

        curViewState?.apply {
            contentParent.removeView(view)
        }

        val oldStateView = curViewState
        curViewState = target

        contentParent.addView(target.view, indexOfContentView, contentLayoutParams)

        onShowStateView(target, oldStateView)
    }

    private val mViewStateListener by lazy {
        mutableListOf<OnViewStateListener>()
    }

    fun addOnViewStateListeners(listener: Collection<OnViewStateListener>) {
        mViewStateListener.addAll(listener)
    }

    fun addOnViewStateListener(listener: OnViewStateListener) {
        mViewStateListener.add(listener)
    }

    fun removeOnViewStateListener(listener: OnViewStateListener) {
        mViewStateListener.remove(listener)
    }

    @CallSuper
    protected fun onShowStateView(viewState: ViewState, oldStateView: ViewState?) {

//        Log.e("TAG", "show: ${viewState}")
        mViewStateListener.onEach {
            it.onChanged(viewState, oldStateView)
        }
    }

    fun showLoading() {
        showStateView(ViewState.Loading(loadingView))
    }

    fun showError(error: Throwable) {
        showStateView(ViewState.Error(error, errorView))
    }

    fun showEmpty() {
        showStateView(ViewState.Empty(emptyView))
    }

    fun showContent() {
        showStateView(ViewState.Content(mContentView))
    }

    sealed class ViewState(val view: View?) {
        class Loading(view: View?) : ViewState(view) {
            override fun hashCode(): Int {
                return view.hashCode()
            }

            override fun toString(): String {
                return "Loading(view=$view)"
            }

            override fun equals(other: Any?): Boolean {
                return other is Loading &&
                        view === other.view
            }
        }

        class Error(val error: Throwable, view: View?) : ViewState(view) {
            override fun hashCode(): Int {
                return view.hashCode() + error.hashCode()
            }

            override fun toString(): String {
                return "Error(view=$view,error=$error)"
            }

            override fun equals(other: Any?): Boolean {
                return other is Error
                        && view === other.view
                        && error === other.error
            }
        }

        class Empty(view: View?) : ViewState(view) {
            override fun hashCode(): Int {
                return view.hashCode()
            }

            override fun toString(): String {
                return "Empty(view=$view)"
            }

            override fun equals(other: Any?): Boolean {
                return other is Empty &&
                        view === other.view
            }
        }

        class Content(view: View?) : ViewState(view) {
            override fun hashCode(): Int {
                return view.hashCode()
            }

            override fun toString(): String {
                return "Content(view=$view)"
            }

            override fun equals(other: Any?): Boolean {
                return other is Content &&
                        view === other.view
            }
        }
    }

    interface OnViewStateListener {
        fun onChanged(state: ViewState, oldStateView: ViewState?)
    }

    open class Builder {
        private var loadingView: View? = null
        private var errorView: View? = null
        private var emptyView: View? = null
        private val contentView: View

        private val mContext: Context
        private val mLayoutInflater: LayoutInflater

        constructor(contentView: View) {
            this.contentView = contentView
            this.mContext = contentView.context
            mLayoutInflater = LayoutInflater.from(this.mContext)
        }

        fun setLoadingLayoutId(@LayoutRes layoutId: Int): Builder {
            return setLoadingView(mLayoutInflater.inflate(layoutId, null, false))
        }

        fun setLoadingView(view: View): Builder {
            this.loadingView = view
            return this
        }

        fun setErrorLayoutId(@LayoutRes layoutId: Int): Builder {
            return setErrorView(mLayoutInflater.inflate(layoutId, null, false))
        }

        fun setErrorView(view: View): Builder {
            this.errorView = view
            return this
        }

        fun setEmptyLayoutId(@LayoutRes layoutId: Int): Builder {
            return setEmptyView(mLayoutInflater.inflate(layoutId, null, false))
        }

        fun setEmptyView(view: View): Builder {
            this.emptyView = view
            return this
        }

        private val mViewStateListener by lazy {
            mutableListOf<OnViewStateListener>()
        }

        fun addOnViewStateListener(listener: OnViewStateListener): Builder {
            mViewStateListener.add(listener)
            return this
        }

        fun build(): StateView {
            if (null == contentView.parent) {
                throw IllegalArgumentException("contentView must attach to parent.")
            }

            val stateView = StateView(contentView, loadingView, errorView, emptyView)

            stateView.addOnViewStateListeners(mViewStateListener)
            return stateView
        }
    }

    class DefaultBuilder : Builder {

        private var onErrorClickListener: OnErrorClickListener? = null
        private var themeViewModel: AppTheme? = null

        constructor(lifecycle: Lifecycle, contentView: View) : super(contentView) {

            setLoadingLayoutId(R.layout.layout_loading)
                .setErrorLayoutId(R.layout.layout_load_error)
                .setEmptyLayoutId(R.layout.layout_data_empty)
                .addOnViewStateListener(object : OnViewStateListener {
                    override fun onChanged(state: ViewState, oldStateView: ViewState?) {

                        state.view?.apply {
                            try {
                                themeViewModel?.apply {
                                    DataBindingUtil.bind<ViewDataBinding>(state.view)
                                        ?.setVariable(BR.themeViewModel, themeViewModel)
                                }
                            } catch (e: java.lang.IllegalArgumentException) {
                                e.printStackTrace()
                            }
                        }

                        if (oldStateView is ViewState.Loading) {
                            oldStateView.view?.apply {
                                findViewById<ImageView>(R.id.iv_loading1).animate().cancel()
                                findViewById<ImageView>(R.id.iv_loading2).animate().cancel()
                                findViewById<ImageView>(R.id.iv_loading3).animate().cancel()
                                findViewById<ImageView>(R.id.iv_loading4).animate().cancel()
                            }
                        }

                        if (state is ViewState.Loading) {
                            state.view?.apply {
                                findViewById<ImageView>(R.id.iv_loading1)
                                    .starAnimator(lifecycle, R.animator.animator_loading1)
                                findViewById<ImageView>(R.id.iv_loading2)
                                    .starAnimator(lifecycle, R.animator.animator_loading2)
                                findViewById<ImageView>(R.id.iv_loading3)
                                    .starAnimator(lifecycle, R.animator.animator_loading3)
                                findViewById<ImageView>(R.id.iv_loading4)
                                    .starAnimator(lifecycle, R.animator.animator_loading4)
                            }
                        } else if (state is ViewState.Error) {
                            state.view?.apply {
                                setOnClickListener {
                                    onErrorClickListener?.onErrorCLick(state.error)
                                }
                            }
                        }
                    }
                })
        }

        fun setOnErrorClickListener(listener: OnErrorClickListener): DefaultBuilder {
            this.onErrorClickListener = listener
            return this
        }

        fun setThemeViewModel(theme: AppTheme?): DefaultBuilder {
            themeViewModel = theme
            return this
        }

    }
}