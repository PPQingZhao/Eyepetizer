package com.pp.library_base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

class DefaultBindingPagingDataAdapter<VB : ViewDataBinding, VM : Any, T : Any>(
    private val getItemViewType: (position: Int) -> Int = { 0 },
    private val onCreateViewModel: (binding: VB, item: T?, cacheItemViewModel: VM?) -> VM?,
    private val onCreateBinding: (parent: ViewGroup, viewType: Int, inflater: LayoutInflater) -> VB,
    diffCallback: DiffUtil.ItemCallback<T>,
) : BindingPagingDataAdapter<VB, VM, T>(diffCallback) {

    override fun getItemViewType(position: Int): Int {
        return getItemViewType.invoke(position)
    }

    override fun createViewModel(binding: VB, item: T?, cacheItemViewModel: VM?): VM? {
        return onCreateViewModel.invoke(binding, item, cacheItemViewModel)
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): VB {
        return onCreateBinding.invoke(parent, viewType, LayoutInflater.from(parent.context))
    }

}
