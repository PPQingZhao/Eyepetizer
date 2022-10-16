package com.pp.library_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_ui.BR

abstract class BindingAdapter<VB : ViewDataBinding, VM : Any, T : Any?> :
    RecyclerView.Adapter<BindingHolder<VB>>() {

    private val dataList by lazy { mutableListOf<T>() }

    fun setDataList(list: List<T>) {
        dataList.clear()
        dataList.addAll(list)
    }

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

        setVariable(holder.binding, createItemViewModel)
    }

    protected fun getItem(position: Int): T? {
        return if (position >= 0 && position < dataList.size) {
            dataList[position]
        } else {
            null
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
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
    open fun onSetVariable(binding: ViewDataBinding, viewModel: VM): Boolean {
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

class BindingHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
