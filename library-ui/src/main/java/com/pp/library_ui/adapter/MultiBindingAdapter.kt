package com.pp.library_ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

open class MultiBindingAdapter<Data : Any> : Adapter<MultiItemViewHolder<Data>>() {

    private val mViewTypeAdapterMap by lazy { mutableMapOf<Int, ViewBindingItem<Data>>() }

    private val dataList by lazy { mutableListOf<Data?>() }

    @SuppressLint("NotifyDataSetChanged")
    open fun setDataList(list: List<Data>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    open fun addDatas(list: List<Data>) {
        dataList.addAll(list)
        notifyItemRangeChanged(dataList.size - 1, list.size)
    }

    fun getDataList(): Collection<Data?> {
        return dataList
    }

    fun addData(index: Int, d: Data?) {
        dataList.add(index, d)
        notifyItemInserted(index)
    }

    fun removeData(d: Data?): Boolean {
        val removeIndex = dataList.indexOf(d)
        if (removeIndex >= 0) {
            removeData(removeIndex)
            return true
        }
        return false
    }

    fun removeData(index: Int): Data?{
        val data = dataList.removeAt(index)
        notifyItemRemoved(index)
        return data
    }


    fun getItem(position: Int): Data? {
        return if (position >= 0 && position < dataList.size) {
            dataList[position]
        } else {
            null
        }
    }

    fun addBindingItem(item: ViewBindingItem<Data>) {
        val type = item.getType()

        if (mViewTypeAdapterMap.containsKey(type)) {
            throw RuntimeException("The item type already exists: {type: ${type}}")
        }
        mViewTypeAdapterMap[type] = item
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
//        Log.e("TAG","getItemViewType: ${position}  count: ${itemCount}")
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

    override fun onViewAttachedToWindow(holder: MultiItemViewHolder<Data>) {
        holder.viewBindingItem.adapterBindingHelper.onViewAttachedToWindow(holder)
    }

    fun removeDataList(removeDatas: MutableList<Data?>) {
        removeDatas.onEach {
            removeData(it)
        }
    }

}