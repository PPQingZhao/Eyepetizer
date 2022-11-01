package com.pp.library_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemDefaultLoadMoreBinding

class CustomLoadMoreStateAdapter(
    @ColorRes val textColor: Int = com.pp.library_ui.R.color.color_text_selected,
    val retry: () -> Unit
) :
    SelfLoadStateAdapter<BindingHolder<ItemDefaultLoadMoreBinding>>() {
    override fun onBindViewHolder(
        holder: BindingHolder<ItemDefaultLoadMoreBinding>,
        loadStates: CombinedLoadStates
    ) {
//        Log.e("DefaultLoadStateAdapter", loadState.toString())

        holder.binding.textColor = holder.binding.root.resources.getColor(textColor)
        holder.binding.loading.visibility =
            if (loadStates.refresh is LoadState.Loading) View.VISIBLE else View.GONE

        holder.binding.loadError.visibility =
            if (loadStates.refresh is LoadState.Error) View.VISIBLE else View.GONE
        // 错误重试
        holder.binding.loadError.setOnClickListener {
            retry()
        }

        holder.binding.loadDataEmpty.visibility =
            if (loadStates.append is LoadState.NotLoading
                && loadStates.append.endOfPaginationReached
                && !(loadStates.refresh is LoadState.Error)
            ) View.VISIBLE else View.GONE

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


}