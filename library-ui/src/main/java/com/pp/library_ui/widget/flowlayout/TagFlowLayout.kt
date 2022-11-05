package com.pp.library_ui.widget.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View

class TagFlowLayout : FlowLayout, TagAdapter.OnDataChangedListener {

    private val mSelectedSet = mutableSetOf<Int>()
    private var mAdapter: TagAdapter<*>? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        0
    )

    private var mSelectListener: ((selectPos: Set<Int>) -> Unit)? = null

    private var mClickListener: ((v: View, pos: Int, parent: FlowLayout) -> Boolean)? = null

    fun setOnTagClickListener(block: ((v: View, pos: Int, parent: FlowLayout) -> Boolean)) {
        mClickListener = block
    }

    fun setOnSelectListener(block: ((selectPos: Set<Int>) -> Unit)) {
        mSelectListener = block
    }

    fun setAdapter(adapter: TagAdapter<*>) {
        mAdapter = adapter
        mAdapter!!.setOnDataChangedListener(this)
        mSelectedSet.clear()
        changeAdapter()
    }

    private fun changeAdapter() {
        removeAllViews()
        val adapter = mAdapter

        adapter?.let {
            val count = it.getCount()

            for (i in 0 until count) {
                val child = it.getView(this@TagFlowLayout, i)

                addView(child)

                child.setOnClickListener {
                    mClickListener?.invoke(child, i, this)
                }
            }
        }
    }

    override fun onChanged() {
        mSelectedSet.clear()
        changeAdapter()
    }

}