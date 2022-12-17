package com.pp.library_ui.widget.indicator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.pp.library_ui.R

abstract class IndicatorView : View {
    private var mIndicatorCount = 0
        set(value) {
            field = value
            requestLayout()
        }

    var selectedPos = 0

    protected var indicationOffset = 0f
    fun getIndicatorCount(): Int {
        return mIndicatorCount
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("Recycle")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (isInEditMode) {
            mIndicatorCount = 3
            selectedPos = 0
        }
        // 解析自定义属性
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.IndicatorView)
        normalColor =
            typedArray.getColor(R.styleable.IndicatorView_indicatorNormalColor, normalColor)

        selectedColor =
            typedArray.getColor(R.styleable.IndicatorView_indicatorSelectedColor, selectedColor)


        // 释放
        typedArray.recycle()
    }


    @ColorInt
    var selectedColor = Color.BLACK

    @ColorInt
    var normalColor = Color.GRAY
    var indicatorSpace = 0f
    protected val mPaint by lazy {
        val paint = Paint()
        paint.isAntiAlias = true
        paint
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w: Int = calculateWidth(widthMeasureSpec)
        val h: Int = calculateHeight(heightMeasureSpec)
        val wMeasureSpec = MeasureSpec.makeMeasureSpec(w, MeasureSpec.getMode(widthMeasureSpec))
        val hMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.getMode(heightMeasureSpec))
        setMeasuredDimension(
            getDefaultSize(w, wMeasureSpec),
            getDefaultSize(h, hMeasureSpec)
        );

    }

    abstract fun calculateHeight(heightMeasureSpec: Int): Int

    abstract fun calculateWidth(widthMeasureSpec: Int): Int

    val onPageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
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
