package com.pp.library_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.pp.library_ui.BR
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemDefaultLoadMoreBinding
import com.pp.library_ui.utils.AppThemeViewModel

class CustomLoadMoreStateAdapter(
    val onRetry: () -> Unit
) :
    SelfLoadStateAdapter<BindingHolder<ItemDefaultLoadMoreBinding>>() {
    override fun onBindViewHolder(
        holder: BindingHolder<ItemDefaultLoadMoreBinding>,
        loadStates: CombinedLoadStates
    ) {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())
/*

        holder.binding.loading.visibility =
            if (!loadStates.append .endOfPaginationReached && loadStates.refresh is LoadState.Loading) View.VISIBLE else View.GONE

        holder.binding.loadError.visibility =
            if (loadStates.refresh is LoadState.Error) View.VISIBLE else View.GONE
        // 错误重试
        holder.binding.loadError.setOnClickListener {
            onRetry()
        }

        holder.binding.loadDataEmpty.visibility =
            if (loadStates.append is LoadState.NotLoading
                && loadStates.append.endOfPaginationReached
                && (loadStates.refresh is LoadState.NotLoading)
            ) View.VISIBLE else View.GONE
*/

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: CombinedLoadStates
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


    override fun displayLoadStateAsItem(loadStates: CombinedLoadStates): Boolean {
        return true //super.displayLoadStateAsItem(loadStates)
    }

    override fun onViewAttachedToWindow(holder: BindingHolder<ItemDefaultLoadMoreBinding>) {
        val lifecycleOwner = ViewTreeLifecycleOwner.get( holder.binding.root)
        holder.binding.lifecycleOwner = lifecycleOwner

        val appTheme = AppThemeViewModel.get( holder.binding.root)
        holder.binding.setVariable(BR.themeViewModel, appTheme)
    }

}