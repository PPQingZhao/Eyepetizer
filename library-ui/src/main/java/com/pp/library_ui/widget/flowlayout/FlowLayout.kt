package com.pp.library_ui.widget.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

open class FlowLayout : ViewGroup {

    // 一行的 view
    private var mLineViews = mutableListOf<View>()

    // 所有行的
    private val mViews = mutableListOf<List<View>>()

    // 每一行的高度
    private val mHeights = mutableListOf<Int>()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mLineViews.clear()
        mViews.clear()
        mHeights.clear()

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var lineWidth = 0
        var lineHeight = 0

        var flowLayoutWidth = 0
        var flowLayoutHeight = 0

        val paddingLeftRight = paddingLeft + paddingRight
        val paddingTopBottom = paddingTop + paddingBottom

        // 测量 child 宽高
        for (i in 0 until childCount) {
            val child = getChildAt(i)

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)

            val lp = child.layoutParams as MarginLayoutParams
            val childMarginLeftRight = lp.leftMargin + lp.rightMargin
            val childMarginTopBottom = lp.topMargin + lp.bottomMargin

            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            if (lineWidth + childWidth + childMarginLeftRight + paddingLeftRight > widthSize) {
                // 换行
                mViews.add(mLineViews)
                mLineViews = mutableListOf()
                flowLayoutWidth = max(flowLayoutWidth, lineWidth)
                flowLayoutHeight += lineHeight
                mHeights.add(lineHeight)
                lineWidth = 0
                lineHeight = 0
            }

            mLineViews.add(child)
            lineWidth += childWidth + childMarginLeftRight

            // 添加最后一行
            if (i == childCount - 1) {
                mViews.add(mLineViews)
                mHeights.add(lineHeight)
                flowLayoutWidth = max(flowLayoutWidth, lineWidth)
                flowLayoutHeight += lineHeight
            }

            lineHeight = max(lineHeight, childHeight + childMarginTopBottom)
        }

        flowLayoutWidth += paddingLeftRight
        flowLayoutHeight += paddingTopBottom

        // 设置自己的宽高
        val w = if (widthMode == MeasureSpec.EXACTLY) widthSize else flowLayoutWidth
        val h = if (heightMode == MeasureSpec.EXACTLY) heightSize else flowLayoutHeight
        setMeasuredDimension(w, h)
    }

    override fun onLayout(chaneed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var curX = paddingLeft
        var curY = paddingTop
        val lineCount = mViews.size
        for (i in 0 until lineCount) {
            val lineViews = mViews.get(i)
            val lineHeight = mHeights.get(i)
            val size = lineViews.size
            for (j in 0 until size) {
                val child = lineViews.get(j)
                val lp = child.layoutParams as MarginLayoutParams

                val left = curX + lp.leftMargin
                val top = curY + lp.topMargin
                val right = left + child.measuredWidth
                val bottom = right + child.measuredHeight

                child.layout(left, top, right, bottom)
                curX += right + lp.rightMargin
            }
            curY += lineHeight
            curX = 0
        }
    }

}