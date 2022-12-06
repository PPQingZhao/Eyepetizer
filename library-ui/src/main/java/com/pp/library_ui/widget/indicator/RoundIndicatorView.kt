package com.pp.library_ui.widget.indicator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log

class RoundIndicatorView(context: Context, attrs: AttributeSet) : IndicatorView(context, attrs) {
    var roundRectF = RectF(0f, 0f, 30f, 10f)
    var roundRadius = roundRectF.height() * 0.5f

    init {
        indicatorSpace = roundRectF.height()
    }


    override fun calculateHeight(heightMeasureSpec: Int): Int {
        return roundRectF.height().toInt()
    }

    override fun calculateWidth(widthMeasureSpec: Int): Int {
        val indicatorCount: Int = getIndicatorCount()
        val roundWidth = roundRectF.width()
        return (indicatorCount * roundWidth + (indicatorCount - 1) * indicatorSpace).toInt()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val roundWidth = roundRectF.width()
        val rectF = RectF(roundRectF)
        var selectedRectF: RectF? = null
        var borderRectF: RectF? = null
        val indicatorCount: Int = getIndicatorCount()
        mPaint.color = normalColor
        for (i in 0 until indicatorCount) {
            canvas.drawRoundRect(rectF, roundRadius, roundRadius, mPaint)
            if (selectedPos == i) {
                selectedRectF = RectF(rectF)
                if (selectedPos == indicatorCount - 1) {
                    selectedRectF.offset(roundWidth * indicationOffset, 0f)
                    borderRectF = RectF(roundRectF)
                    borderRectF.offset(-(1 - indicationOffset) * roundWidth, 0f)
                } else {
                    selectedRectF.offset((indicatorSpace + roundWidth) * indicationOffset, 0f)
                }
            }
            rectF.offset(indicatorSpace + roundWidth, 0f)
        }
        mPaint.color = selectedColor
        if (null != selectedRectF) {
            canvas.drawRoundRect(selectedRectF, roundRadius, roundRadius, mPaint)
        }
        if (null != borderRectF) {
            canvas.drawRoundRect(borderRectF, roundRadius, roundRadius, mPaint)
        }

//        Log.e("TAG","width: ${width}   height: ${height}")
    }
}