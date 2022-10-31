package com.pp.library_ui.widget.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBannerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected var mList: MutableList<T> = mutableListOf()
    var isCanLoop = false
    var pagerClickListener: OnPagerClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return createViewHolder(parent, inflater, viewType)
    }

    abstract fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val realPosition: Int = getRealPosition(position)
        holder.itemView.setOnClickListener {
            pagerClickListener?.onPageClick(realPosition)
        }
        onBind(holder, mList[realPosition], realPosition, mList.size)
    }

    abstract fun onBind(holder: VH, data: T, realPosition: Int, size: Int)

    override fun getItemCount(): Int {
        return if (isCanLoop && mList.size > 1) {
            Int.MAX_VALUE
        } else {
            mList.size
        }
    }

    fun getData(): List<T> {
        return mList
    }

    fun setData(list: List<T>) {
        mList.clear()
        mList.addAll(list)
    }

    fun getListSize(): Int {
        return mList.size
    }

    fun getRealPosition(position: Int): Int {
        val pageSize = mList.size
        if (pageSize == 0) {
            return 0
        }
        return if (isCanLoop) (position + pageSize) % pageSize else position
    }

    interface OnPagerClickListener {
        fun onPageClick(position: Int)
    }

}