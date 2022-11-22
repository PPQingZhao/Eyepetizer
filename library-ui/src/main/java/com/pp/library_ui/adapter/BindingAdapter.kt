package com.pp.library_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_ui.BR

abstract class BindingAdapter<VB : ViewDataBinding, VM : Any, T : Any> :
    RecyclerView.Adapter<BindingHolder<VB>>() {

    private val dataList by lazy { mutableListOf<T>() }

    fun setDataList(list: List<T>) {
        dataList.clear()
        dataList.addAll(list)
    }

    private val bindingHelper: AdapterBindingHelper<VB, VM, T> by lazy {
        object : AdapterBindingHelper<VB, VM, T>() {
            override fun createViewModel(binding: VB, item: T?, cacheItemViewModel: VM?): VM? {
                return this@BindingAdapter.createViewModel(binding, item, cacheItemViewModel)
            }

            override fun createBinding(parent: ViewGroup, viewType: Int): VB {
                return this@BindingAdapter.createBinding(parent, viewType)
            }

            override fun onSetVariable(binding: VB, viewModel: VM?): Boolean {
                return this@BindingAdapter.onSetVariable(binding, viewModel)
            }

        }
    }

    override fun onBindViewHolder(holder: BindingHolder<VB>, position: Int) {

        bindingHelper.bind(holder, position, getItem(position))
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


    var layoutInflater: LayoutInflater? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        layoutInflater = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<VB> {
        return BindingHolder<VB>(createBinding(parent, viewType))
    }

    /**
     * 在这里设置 ViewDataBinding::setVariable(int variableId, @Nullable Object value);
     */
    open fun onSetVariable(binding: ViewDataBinding, viewModel: VM?): Boolean {
        return false
    }

    /**
     * 创建viewModel
     */
    abstract fun createViewModel(
        binding: VB,
        item: T?,
        cacheItemViewModel: VM?
    ): VM?

    /**
     * 创建viewType类型的ViewDataBinding
     */
    abstract fun createBinding(parent: ViewGroup, viewType: Int): VB


}
