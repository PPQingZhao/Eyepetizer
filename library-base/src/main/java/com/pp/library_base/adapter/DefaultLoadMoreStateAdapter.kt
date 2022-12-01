package com.pp.library_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.pp.library_ui.BR
import com.pp.library_ui.R
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemDefaultLoadMoreBinding
import com.pp.library_ui.utils.AppThemeViewModel
import com.pp.library_ui.utils.DefaultAnimationListener

class DefaultLoadMoreStateAdapter(
    lifecycle: Lifecycle? = null,
    var retry: (() -> Unit)? = null,
) :
    LoadStateAdapter<BindingHolder<ItemDefaultLoadMoreBinding>>() {

    init {

        lifecycle?.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                retry = null
            }
        })
    }

    override fun onBindViewHolder(
        holder: BindingHolder<ItemDefaultLoadMoreBinding>,
        loadState: LoadState
    ) {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())
        if (loadState is LoadState.Loading) {
            holder.binding.loading.visibility = View.VISIBLE
            val load1 =
                AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.anim_loading1)
            val load2 =
                AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.anim_loading2)
            val load3 =
                AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.anim_loading3)
            val load4 =
                AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.anim_loading4)
            load2.setAnimationListener(DefaultAnimationListener(onAnimationEnd = {
                holder.binding.ivLoading2
                    .startAnimation(
                        AnimationUtils.loadAnimation(
                            holder.binding.root.context,
                            R.anim.anim_loading1
                        )
                    )
            }))

            load3.setAnimationListener(DefaultAnimationListener(onAnimationEnd = {
                holder.binding.ivLoading3.startAnimation(
                    AnimationUtils.loadAnimation(
                        holder.binding.root.context,
                        R.anim.anim_loading1
                    )
                )
            }))

            load4.setAnimationListener(DefaultAnimationListener(onAnimationEnd = {
                holder.binding.ivLoading4.startAnimation(
                    AnimationUtils.loadAnimation(
                        holder.binding.root.context,
                        R.anim.anim_loading1
                    )
                )
            }))
            holder.binding.ivLoading1.startAnimation(load1)
            holder.binding.ivLoading2.startAnimation(load2)
            holder.binding.ivLoading3.startAnimation(load3)
            holder.binding.ivLoading4.startAnimation(load4)

        } else {
            holder.binding.ivLoading1.animation?.cancel()
            holder.binding.ivLoading2.animation?.cancel()
            holder.binding.ivLoading3.animation?.cancel()
            holder.binding.ivLoading4.animation?.cancel()
            holder.binding.loading.visibility = View.GONE
        }

        holder.binding.loadError.visibility =
            if (loadState is LoadState.Error) View.VISIBLE else View.GONE
        // 错误重试
        holder.binding.loadError.setOnClickListener {
            retry?.invoke()
        }

        holder.binding.loadDataEmpty.visibility =
            if (loadState is LoadState.NotLoading && loadState.endOfPaginationReached) View.VISIBLE else View.GONE

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BindingHolder<ItemDefaultLoadMoreBinding> {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())
        return BindingHolder(
            ItemDefaultLoadMoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
//        Log.e("DefaultLoadStateAdapter", "displayLoadStateAsItem:   ${loadState.toString()}")
        // 父类默认实现: loading 或者 err 显示
        return super.displayLoadStateAsItem(loadState)
                // no data: not loading 并且 已到底部 (endOfPaginationReached) 显示
                || (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
    }

    override fun onViewAttachedToWindow(holder: BindingHolder<ItemDefaultLoadMoreBinding>) {
        val lifecycleOwner = ViewTreeLifecycleOwner.get(holder.binding.root)
        holder.binding.lifecycleOwner = lifecycleOwner

        val appTheme = AppThemeViewModel.get(holder.binding.root)
        holder.binding.setVariable(BR.themeViewModel, appTheme)
    }


}