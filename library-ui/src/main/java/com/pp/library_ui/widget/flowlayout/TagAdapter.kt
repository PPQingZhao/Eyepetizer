package com.pp.library_ui.widget.flowlayout

import android.view.View

abstract class TagAdapter<T>(val mList: List<T>) {

    private var mOnDataChangedListener: OnDataChangedListener? = null

    interface OnDataChangedListener {
        fun onChanged()
    }

    fun setOnDataChangedListener(l: OnDataChangedListener) {
        mOnDataChangedListener = l
    }

    fun getCount(): Int {
        return mList.size
    }

    fun getItem(pos: Int): T {
        return mList[pos]
    }

    abstract fun getView(parent: FlowLayout, pos: Int): View

    fun notifyDataChanged() {
        mOnDataChangedListener?.onChanged()
    }

    fun onSelected(pos: Int, v: View) {

    }

    fun unSelected(pos: Int, v: View) {

    }
}
