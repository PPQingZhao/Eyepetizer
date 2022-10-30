package com.pp.library_ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

class DefaultViewBindingItem<Data : Any?>(
    private val type: Int,
    private val isRight: (item: Data?) -> Boolean,
    private val onCreateViewDataBinding: (parent: ViewGroup) -> ViewDataBinding,
    private val onCreateViewModel: (binding: ViewDataBinding, item: Data?, cacheItemViewModel: Any?) -> Any?
) :
    ViewBindingItem<Data>() {

    override fun onCreateViewDataBinding(parent: ViewGroup): ViewDataBinding {
        return onCreateViewDataBinding.invoke(parent)
    }

    override fun onCreateViewModel(
        binding: ViewDataBinding,
        item: Data?,
        cacheItemViewModel: Any?
    ): Any? {
        return onCreateViewModel.invoke(binding, item, cacheItemViewModel)
    }

    override fun getType(): Int {
        return type
    }

    override fun isRight(item: Data?): Boolean {
        return isRight.invoke(item)
    }

}