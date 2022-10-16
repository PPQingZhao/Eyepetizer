package com.pp.library_ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class ExpandTextLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {

    }

    override fun onViewAdded(child: View?) {
        throw java.lang.IllegalStateException(" ${javaClass.name} does not support direct child views")
    }
}