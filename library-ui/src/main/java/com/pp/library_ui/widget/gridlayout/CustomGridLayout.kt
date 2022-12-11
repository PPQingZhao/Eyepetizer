package com.pp.library_ui.widget.gridlayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import com.pp.library_ui.utils.load
import kotlin.math.ceil

class CustomGridLayout : ViewGroup {
    /**
     * 1. 只有一张图，只显示一个
     * 2. 两张图，左边显示一个，右边显示一个
     * 3. 四张图，2*2 显示，
     *    超过四张图，第四张图显示 +
     */

    // 间隙宽度
    private var mSpacing = 4f
    private var mRow = 1
    private var mColumn = 1
    private var mWidth = 0
    private var mHeight = 0
    private var hasLayout = false

    private val mUrlList = mutableListOf<String>()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    override fun onLayout(changed: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        mWidth = width
        mHeight = height

        if (!hasLayout) {
            notifyDataSetChanged()
            hasLayout = true
        }
    }

    private fun notifyDataSetChanged() {
        if (mUrlList.isEmpty()) {
            visibility = View.GONE
            return
        }
        val size = mUrlList.size
        getSplitMode(size)

        for (i in 0 until size) {
            val url = mUrlList[i]
            if (i < mRow * mColumn) {
                val imageView = createImageView(url, i)
                layoutImageView(imageView, url, i)
            }
        }
    }
    private fun createImageView(url: String, pos: Int): ImageView {
        val imageView = ShapeableImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        val posArr = getPosition(pos)
        val row = posArr[0]
        val col = posArr[1]

        var isLeft = false
        var isRight = false
        var isTop = false
        var isBottom = false

        if (mRow == mColumn) {
            if (mRow == 1) {
                isLeft = true
                isRight = true
                isTop = true
                isBottom = true
            } else {
                when (col) {
                    0 -> isLeft = true
                    1 -> isRight = true
                }
                when (row) {
                    0 -> isTop = true
                    1 -> isBottom = true
                }
            }
        } else {
            isTop = true
            isBottom = true
            when (col) {
                0 -> isLeft = true
                1 -> isRight = true
            }
        }

        setShape(imageView, isLeft, isRight, isTop, isBottom)

        return imageView
    }

    private fun setShape(
        imageView: ShapeableImageView,
        left: Boolean,
        right: Boolean,
        top: Boolean,
        bottom: Boolean
    ) {
        val leftTop = left && top
        val rightTop = right && top
        val leftBottom = left && bottom
        val rightBottom = right && bottom

        val radius = 10f

        // set shape
        val shapeBuilder = ShapeAppearanceModel.builder()
        if (leftTop) {
            shapeBuilder.setTopLeftCornerSize(radius)
        }
        if (rightTop) {
            shapeBuilder.setTopRightCornerSize(radius)
        }
        if (leftBottom) {
            shapeBuilder.setBottomLeftCornerSize(radius)
        }
        if (rightBottom) {
            shapeBuilder.setBottomRightCornerSize(radius)
        }
        imageView.shapeAppearanceModel = shapeBuilder.build()
    }

    @SuppressLint("SetTextI18n")
    private fun layoutImageView(imageView: ImageView, url: String, pos: Int) {
        val itemWidth = if (mUrlList.size == 1) mWidth else (mWidth - mSpacing) / 2
        val itemHeight = if (mUrlList.size <= 2) mHeight else (mHeight - mSpacing) / 2

        val posArr = getPosition(pos)
        val row = posArr[0]
        val col = posArr[1]

        val left = (col * itemWidth.toInt() + col * mSpacing).toInt()
        val right = left + itemWidth.toInt()
        val top = (row * itemHeight.toInt() + row * mSpacing).toInt()
        val bottom = top + itemHeight.toInt()

        addView(imageView)

        imageView.layout(left, top, right, bottom)

        val showSize = mRow * mColumn
        val count = mUrlList.size - showSize
        if (pos == (showSize - 1) && count > 0) {
            // show count
            val fontSize = 18f
            val textView = TextView(context).apply {
                setPadding(0, (bottom - top) / 2 - getFontHeight(fontSize), 0, 0)
                setTextColor(Color.WHITE)
                text = "+$count"
                textSize = fontSize
                gravity = Gravity.CENTER
                setBackgroundColor(Color.BLACK)
                background.alpha = 120
            }

            val params = LayoutParams(right - left, bottom - top)
            addView(textView, params)
            textView.layout(left, top, right, bottom)
        }

        displayImage(imageView, url)
    }

    private fun getFontHeight(textSize: Float): Int {
            val paint = Paint()
            paint.textSize = textSize
            val fm = paint.fontMetrics
            return ceil(fm.descent - fm.ascent).toInt()
    }

    private fun displayImage(imageView: ImageView, url: String) {
        imageView.load(url)
    }

    private fun getPosition(pos: Int): IntArray {
        val row: Int
        val col: Int
        if (mRow == mColumn) {
            row = pos / mRow
            col = pos % mColumn
        } else {
            row = 0
            col = pos % mColumn
        }
        return intArrayOf(row, col)
    }

    private fun getSplitMode(size: Int) {
        when (size) {
            1 -> {
                mRow = 1
                mColumn = 1
            }
            2 -> {
                mRow = 1
                mColumn = 2
            }
            else -> {
                mRow = 2
                mColumn = 2
            }
        }
    }

    fun setUrls(urls: List<String>) {
        mUrlList.clear()
        mUrlList.addAll(urls)
        hasLayout = false
        requestLayout()
    }

    fun setSpacing(spacing: Float) {
        mSpacing = spacing
    }
}