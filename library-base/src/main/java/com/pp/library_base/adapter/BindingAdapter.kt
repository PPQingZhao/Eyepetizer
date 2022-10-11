package com.pp.library_base.adapter

import android.util.ArrayMap
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pp.mvvm.BR

abstract class BindingAdapter<VM : Any, T : Any>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, Holder>(diffCallback) {
    private val itemViewModelCaches by lazy { ArrayMap<Int, VM>() }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        // position 位置缓存的 item viewModel
        val cacheItemViewModel = itemViewModelCaches[position]
        // 创建 item viewModle
        val createItemViewModel = createItemViewModel(
            getItemViewType(position),
            getItem(position),
            cacheItemViewModel
        )
        // 更新
        itemViewModelCaches[position] = createItemViewModel

        holder.mBinding?.setVariable(
            BR.viewModel,
            createItemViewModel
        )

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(createItemView(parent, viewType))
    }


    /**
     * 创建 item viewModel
     */
    abstract fun createItemViewModel(itemViewType: Int, item: T?, cacheItemViewModel: VM?): VM?

    /**
     * 创建item view
     */
    abstract fun createItemView(parent: ViewGroup, viewType: Int): View


}

class Holder(item: View) : RecyclerView.ViewHolder(item) {
    val mBinding by lazy { DataBindingUtil.bind<ViewDataBinding>(itemView) }
}
