package com.pp.library_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemDefaultLoadMoreBinding

class DefaultLoadMoreStateAdapter(
    @ColorRes val textColor: Int = com.pp.library_ui.R.color.color_text_selected,
    val retry: () -> Unit
) :
    LoadStateAdapter<BindingHolder<ItemDefaultLoadMoreBinding>>() {
    override fun onBindViewHolder(
        holder: BindingHolder<ItemDefaultLoadMoreBinding>,
        loadState: LoadState
    ) {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())

        holder.binding.loading.visibility =
            if (loadState is LoadState.Loading) View.VISIBLE else View.GONE

        holder.binding.loadError.visibility =
            if (loadState is LoadState.Error) View.VISIBLE else View.GONE
        // 错误重试
        holder.binding.loadError.setOnClickListener {
            retry()
        }

        holder.binding.loadDataEmpty.visibility =
            if (loadState is LoadState.NotLoading && loadState.endOfPaginationReached) View.VISIBLE else View.GONE

        holder.binding.loadDataEmpty.setTextColor(holder.binding.root.resources.getColor(textColor))
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
                // not loading 并且 no data (endOfPaginationReached) 显示
                || (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
    }


}