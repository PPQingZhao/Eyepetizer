package com.pp.library_base.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_ui.adapter.MultiItemViewHolder
import com.pp.library_ui.adapter.ViewBindingItem

class MultiBindingPagingDataAdapter<Data : Any>(
    diffCallback: DiffUtil.ItemCallback<Data>
) :
    PagingDataAdapter<Data, MultiItemViewHolder<Data>>(diffCallback) {

    private val mViewTypeAdapterMap by lazy { mutableMapOf<Int, ViewBindingItem<Data>>() }

    fun addBindingItem(item: ViewBindingItem<Data>) {
        val type = item.getType()

        if (mViewTypeAdapterMap.containsKey(type)) {
            throw RuntimeException("The item type already exists: {type: ${type}}")
        }
        mViewTypeAdapterMap[type] = item
    }

    override fun getItemViewType(position: Int): Int {

        val item = getItem(position)
        // 查找 item type
        for (entry in mViewTypeAdapterMap) {
            if (entry.key == entry.value.getItemType(position, item)) {
                return entry.key
            }
        }

        throw RuntimeException("ViewBindingItem not found for {position: ${position} item: ${item}}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiItemViewHolder<Data> {
        val viewBindingItem = mViewTypeAdapterMap[viewType]!!

        return MultiItemViewHolder(
            viewBindingItem,
            viewBindingItem.adapterBindingHelper.createBinding(parent, viewType)
        )
    }

    override fun onBindViewHolder(holder: MultiItemViewHolder<Data>, position: Int) {
        holder.viewBindingItem.adapterBindingHelper.bind(holder, position, getItem(position))
    }


}
