package com.pp.library_ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.pp.library_ui.BR

abstract class AdapterBindingHelper<VB : ViewDataBinding, VM : Any, T : Any> {
    private val itemViewModelCaches by lazy { mutableMapOf<Int, VM>() }

    fun bind(holder: BindingHolder<VB>, position: Int, item: T?){
        // position 位置缓存的 item viewModel
        val cacheItemViewModel = itemViewModelCaches[position]
        // 创建 item viewModle
        val createItemViewModel = createViewModel(
            holder.binding,
            item,
            cacheItemViewModel
        )
        // 更新
        itemViewModelCaches[position] = createItemViewModel

        setVariable(holder.binding,createItemViewModel)
    }

   private fun setVariable(binding: VB, viewModel: VM) {
        val result = onSetVariable(binding, viewModel)
        if (!result) {
            //set default variable
            try {
                binding.setVariable(BR.viewModel, viewModel)
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 在这里设置 ViewDataBinding::setVariable(int variableId, @Nullable Object value);
     */
    open fun onSetVariable(binding: ViewDataBinding, viewModel: VM):Boolean {
        return false
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