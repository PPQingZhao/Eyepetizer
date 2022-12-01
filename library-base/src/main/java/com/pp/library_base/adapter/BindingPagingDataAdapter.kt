package com.pp.library_base.adapter

import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_ui.adapter.AdapterBindingHelper
import com.pp.library_ui.adapter.BindingHolder

abstract class BindingPagingDataAdapter<VB : ViewDataBinding, VM : Any, T : Any>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BindingHolder<VB>>(diffCallback) {

    private val bindingHelper: AdapterBindingHelper<VB, VM, T> by lazy {
        object : AdapterBindingHelper<VB, VM, T>() {
            override fun createViewModel(binding: VB, item: T?, cacheItemViewModel: VM?): VM? {
                return this@BindingPagingDataAdapter.createViewModel(
                    binding,
                    item,
                    cacheItemViewModel
                )
            }

            override fun createBinding(parent: ViewGroup, viewType: Int): VB {
                return this@BindingPagingDataAdapter.createBinding(parent, viewType)
            }

            override fun onSetVariable(binding: VB, viewModel: VM?): Boolean {
                return this@BindingPagingDataAdapter.onSetVariable(binding, viewModel)
            }

        }
    }

    fun getItemData(@IntRange(from = 0) position: Int) = peek(position)
    open fun onSetVariable(binding: VB, viewModel: VM?): Boolean {
        return false
    }

    override fun onBindViewHolder(holder: BindingHolder<VB>, position: Int) {
        bindingHelper.bind(holder, position, getItem(position))
    }

    abstract fun createViewModel(binding: VB, item: T?, cacheItemViewModel: VM?): VM?
    abstract fun createBinding(parent: ViewGroup, viewType: Int): VB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<VB> {
        return BindingHolder<VB>(bindingHelper.createBinding(parent, viewType))
    }

    override fun onViewAttachedToWindow(holder: BindingHolder<VB>) {
        bindingHelper.onViewAttachedToWindow(holder)
    }
}
