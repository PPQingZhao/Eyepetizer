package com.pp.library_ui.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

abstract class IndicatorView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var mIndicatorCount = 0
        set(value) {
            field = value
            requestLayout()
        }

    var selectedPos = 0

    var indicationOffset = 0f
    fun getIndicatorCount(): Int {
        return mIndicatorCount
    }

    init {
        if (isInEditMode) {
            mIndicatorCount = 3
            selectedPos = 0
        }
    }

    @ColorInt
    var selectedColor = Color.BLACK

    @ColorInt
    var normalColor = Color.GRAY
    var indicatorSpace = 0f
    val mPaint by lazy {
        val paint = Paint()
        paint.isAntiAlias = true
        paint
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w: Int = calculateWidth(widthMeasureSpec)
        val h: Int = calculateHeight(heightMeasureSpec)
        val wMeasureSpec = MeasureSpec.makeMeasureSpec(w, MeasureSpec.getMode(widthMeasureSpec))
        val hMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.getMode(heightMeasureSpec))
        super.onMeasure(wMeasureSpec, hMeasureSpec)
    }

    abstract fun calculateHeight(heightMeasureSpec: Int): Int

    abstract fun calculateWidth(widthMeasureSpec: Int): Int

    val onPageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            selectedPos = position
            indicationOffset = positionOffset
            invalidate()
        }
    }

    fun initIndicator(count: Int) {
        this.mIndicatorCount = count
    }

}
