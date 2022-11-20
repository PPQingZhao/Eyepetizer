package com.pp.library_base.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemDefaultLoadMoreBinding

class DefaultLoadMoreStateAdapter(
    @ColorRes val textColor: Int = com.pp.library_ui.R.color.color_text_selected,
    @ColorInt val tint: Int = Color.BLACK,
    val retry: () -> Unit
) :
    LoadStateAdapter<BindingHolder<ItemDefaultLoadMoreBinding>>() {
    override fun onBindViewHolder(
        holder: BindingHolder<ItemDefaultLoadMoreBinding>,
        loadState: LoadState
    ) {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())
        holder.binding.textColor = holder.binding.root.resources.getColor(textColor)
        holder.binding.loading.visibility =
            if (loadState is LoadState.Loading) View.VISIBLE else View.GONE

        holder.binding.loadIvError.imageTintList = ColorStateList.valueOf(tint)
        holder.binding.loadError.visibility =
            if (loadState is LoadState.Error) View.VISIBLE else View.GONE
        // 错误重试
        holder.binding.loadError.setOnClickListener {
            retry()
        }

        holder.binding.loadDataEmpty.imageTintList = ColorStateList.valueOf(tint)
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


}