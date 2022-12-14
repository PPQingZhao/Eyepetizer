package com.pp.library_ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.pp.library_ui.R
import kotlin.properties.Delegates

class ExpandableTextLayout : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("Recycle")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {

        orientation = VERTICAL
        mMaxLinesTextView = TextView(context, attrs, defStyleAttr)
        attachViewToParent(mMaxLinesTextView,
            0,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))

        val isExpand = mMaxLinesTextView.maxLines == Int.MAX_VALUE
        mMaxLines = mMaxLinesTextView.maxLines

        mExpandTextView = TextView(context)

        mExpandTextView.setPadding(10,10,10,10)
        val layoutParams =
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.END
        attachViewToParent(mExpandTextView,
            1,
            layoutParams)

        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextLayout)
        expandText = typedArray.getString(R.styleable.ExpandableTextLayout_expandText)
        closeText = typedArray.getString(R.styleable.ExpandableTextLayout_closeText)
        val expandTextColor =
            typedArray.getColor(R.styleable.ExpandableTextLayout_expandedTextColor, Color.BLACK)
        val expandTextSize =
            typedArray.getDimensionPixelSize(R.styleable.ExpandableTextLayout_expandedTextSize, 13)
        this.isExpand = typedArray.getBoolean(R.styleable.ExpandableTextLayout_expand, isExpand)
        typedArray.recycle()

        mExpandTextView.apply {

            setTextSize(TypedValue.COMPLEX_UNIT_PX, expandTextSize.toFloat())
            setTextColor(expandTextColor)
            setOnClickListener {
                this@ExpandableTextLayout.isExpand = !this@ExpandableTextLayout.isExpand
            }
        }

    }

    private var mMaxLines = Int.MAX_VALUE
    var expandText: String?
    var closeText: String?
    private var mMaxLinesTextView: TextView by Delegates.notNull()
    private var mExpandTextView: TextView by Delegates.notNull()

    fun isExpandable(): Boolean {
        return mMaxLines != Int.MAX_VALUE
    }

    // 是否展开
    var isExpand = false
        set(value) {
            field = value

            mMaxLinesTextView?.apply {
                maxLines = if (value) Int.MAX_VALUE else mMaxLines
            }
            mExpandTextView?.apply {
                text = if (value) closeText else expandText
            }
        }

    fun setContent(content: String?) {
        mMaxLinesTextView!!.text = content
    }

    fun setExpandTextColor(@ColorInt color: Int) {
        mMaxLinesTextView!!.setTextColor(color)
    }

    private fun updateExpandTextVisibility() {

        if (!isExpandable()) {
            mExpandTextView?.visibility = View.GONE
            return
        }

        val lineCount = mMaxLinesTextView!!.lineCount
        if (lineCount <= 0) {
            mExpandTextView?.visibility = View.GONE
            return
        }

        if (lineCount > mMaxLines) {
            mExpandTextView?.visibility = View.VISIBLE
            return
        }

        // 省略文字数量大于 0
        if (mMaxLinesTextView!!.layout.getEllipsisCount(lineCount - 1) > 0) {
            mExpandTextView?.visibility = View.VISIBLE
            return
        }
        mExpandTextView?.visibility = View.GONE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        updateExpandTextVisibility()
    }


    override fun onViewAdded(child: View?) {
        throw IllegalArgumentException("not support add view")
    }

}