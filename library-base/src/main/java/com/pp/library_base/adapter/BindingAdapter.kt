package com.pp.library_base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pp.mvvm.BR

abstract class BindingAdapter<VB : ViewDataBinding, VM : Any, T : Any>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BindingHolder<VB>>(diffCallback) {

    private val itemViewModelCaches by lazy { mutableMapOf<Int, VM>() }
    override fun onBindViewHolder(holder: BindingHolder<VB>, position: Int) {

        // position 位置缓存的 item viewModel
        val cacheItemViewModel = itemViewModelCaches[position]
        // 创建 item viewModle
        val createItemViewModel = createViewModel(
            holder.binding,
            getItem(position),
            cacheItemViewModel
        )
        // 更新
        itemViewModelCaches[position] = createItemViewModel

        onSetVariable(holder.binding, createItemViewModel)
    }

    lateinit var layoutInflater: LayoutInflater
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
//        layoutInflater = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<VB> {
        return BindingHolder<VB>(createBinding(parent, viewType))
    }

    /**
     * 在这里设置 ViewDataBinding::setVariable(int variableId, @Nullable Object value);
     */
    open fun onSetVariable(binding: ViewDataBinding, viewModel: VM) {
        //set default variable
        try {
            binding.setVariable(BR.viewModel, viewModel)
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    /**
     * 创建viewModel
     */
    abstract fun createViewModel(
        binding: VB,
        item: T?,
        cacheItemViewModel: VM?
    ): VM

    /**
     * 创建viewType类型的ViewDataBinding
     */
    abstract fun createBinding(parent: ViewGroup, viewType: Int): VB


}

class BindingHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
