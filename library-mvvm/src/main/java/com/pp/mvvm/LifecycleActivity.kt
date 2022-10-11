package com.pp.mvvm

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager.LayoutParams
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner

abstract class LifecycleActivity<VB : ViewDataBinding, VM : LifecycleViewModel> :
    FragmentActivity() {
    protected val mBinding: VB by lazy {
        DataBindingUtil.inflate<VB>(
            LayoutInflater.from(this),
            getLayoutRes(),
            null,
            false
        )
    }

    val mViewModel by lazy { ViewModelProvider(this)[(getModelClazz())] }

    abstract fun getModelClazz(): Class<VM>

    abstract @LayoutRes
    fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        lifecycle.addObserver(mViewModel)
        mBinding.setLifecycleOwner { this.lifecycle }
        ViewTreeLifecycleOwner.set(mBinding.root, this)
        ViewTreeViewModelStoreOwner.set(mBinding.root, this)
        mBinding.setVariable(BR.viewModel, mViewModel)
    }

    lateinit var windowInsets: WindowInsets
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setTranslucent()
        /*
            windowInsets分发:拦截WindowInsets, 将windowInsets分发给每个fragment
            沉浸式状态栏: window flags = LayoutParams.FLAG_TRANSLUCENT_STATUS 配合布局中 android:fitsSystemWindows="true"进行实现
        */
        (mBinding.root.parent as View).setOnApplyWindowInsetsListener { v, insets ->

//            Log.e("TAG", "setOnApplyWindowInsetsListener")
            windowInsets = WindowInsets(insets)

//            //
//            if (!dispatchApplyWindowInsetsForFragment(supportFragmentManager, windowInsets)) {
            mBinding.root.dispatchApplyWindowInsets(windowInsets)
//            }

            // 返回一个已消费的 windowInsets,结束分发流程
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowInsets.CONSUMED
            } else {
                insets.consumeSystemWindowInsets()
            }
        }

        /*
              fragment 布局创建完成时,分发windowInsets
         */
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                Log.e("TAG", "onFragmentViewCreated==>> ${f}")
                f.view?.let { view ->
                    ViewCompat.dispatchApplyWindowInsets(
                        view,
                        WindowInsetsCompat.toWindowInsetsCompat(WindowInsets(windowInsets))
                    )
                }
            }
        }, true)
    }

    private fun setTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    private fun dispatchApplyWindowInsetsForFragment(
        manager: FragmentManager,
        insets: WindowInsets
    ): Boolean {
        val consume: Boolean = false
        manager.fragments.forEach { f ->
            f.view?.let { view ->
                ViewCompat.dispatchApplyWindowInsets(
                    view,
                    WindowInsetsCompat.toWindowInsetsCompat(insets)
                ).isConsumed
            }?.let { consume.or(it) }

            consume.or(dispatchApplyWindowInsetsForFragment(f.childFragmentManager, insets))
        }
        return consume
    }

}