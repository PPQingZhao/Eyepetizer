package com.pp.library_ui.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.Orientation

open class DividerDecoration(
    private val dividerSize: Int = 5,
    @ColorInt colorInt: Int = Color.GRAY,
    @Orientation orientation: Int,
    private val canDraw: (viewHolder: RecyclerView.ViewHolder?, type: Int) -> Boolean
) :
    ItemDecoration() {
    val dividerDawer: DividerDrawer

    val paint: Paint

    init {
        dividerDawer = if (orientation == RecyclerView.VERTICAL) VerticalDividerDrawer()
        else HorizontalDividerDrawer()

        paint = Paint()
        paint.color = colorInt
        paint.style = Paint.Style.STROKE
    }

    companion object {
        const val DIVIDER_LEFT = 0
        const val DIVIDER_TOP = 1
        const val DIVIDER_RIGHT = 2
        const val DIVIDER_BOTTOM = 3
    }

    @IntDef(value = intArrayOf(DIVIDER_LEFT, DIVIDER_TOP, DIVIDER_RIGHT, DIVIDER_BOTTOM))
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class DivideType {
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.getChildViewHolder(view)
        val drawLeft = canDraw(viewHolder, DIVIDER_LEFT)
        val drawTop = canDraw(viewHolder, DIVIDER_TOP)
        val drawRight = canDraw(viewHolder, DIVIDER_RIGHT)
        val drawBottom = canDraw(viewHolder, DIVIDER_BOTTOM)

        outRect.set(
            if (drawLeft) dividerSize else 0,
            if (drawTop) dividerSize else 0,
            if (drawRight) dividerSize else 0,
            if (drawBottom) dividerSize else 0
        )
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        c.save()
        if (parent.clipToPadding) {
            /* c.clipRect(
                 parent.paddingLeft.toFloat(),
                 parent.paddingTop.toFloat(),
                 (parent.width - parent.paddingRight).toFloat(),
                 (parent.height - parent.bottom).toFloat()
             )*/
        }

        var index = 0
        val outRect: Rect = Rect()
        while (index < parent.childCount) {

            val view = parent.getChildAt(index)
            parent.layoutManager?.getDecoratedBoundsWithMargins(view, outRect)
            val leftDividerSize = parent.layoutManager?.getLeftDecorationWidth(view) ?: 0
            val topDividerSize = parent.layoutManager?.getTopDecorationHeight(view) ?: 0
            val rightDividerSize = parent.layoutManager?.getRightDecorationWidth(view) ?: 0
            val bottomDividerSize = parent.layoutManager?.getBottomDecorationHeight(view) ?: 0

            // draw left divider
            if (leftDividerSize > 0) {
                paint.strokeWidth = leftDividerSize.toFloat()
                dividerDawer.drawLeftDivider(c, paint, view, outRect)
            }

            // draw top divider
            if (topDividerSize > 0) {
                paint.strokeWidth = topDividerSize.toFloat()
                dividerDawer.drawTopDivider(c, paint, view, outRect)
            }

            //draw right divider
            if (rightDividerSize > 0) {
                paint.strokeWidth = rightDividerSize.toFloat()
                dividerDawer.drawRightDivider(c, paint, view, outRect)
            }

            // draw bottom divider
            if (bottomDividerSize > 0) {
                paint.strokeWidth = bottomDividerSize.toFloat()
                dividerDawer.drawBottomDivider(c, paint, view, outRect)
            }
            index++
        }
        c.restore()
    }


}

class VerticalDividerDrawer : DividerDrawer() {
    override fun drawLeftDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        c.save()
        val startX = decoratedBoundsWithMargins.left.toFloat()
        val startY = decoratedBoundsWithMargins.top.toFloat()
        val stopX = startX
        val stopY = decoratedBoundsWithMargins.bottom.toFloat()
        c.drawLine(startX, startY, stopX, stopY, paint)
        c.restore()
    }

    override fun drawTopDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        c.save()
        val startX = decoratedBoundsWithMargins.left.toFloat()
        val startY = decoratedBoundsWithMargins.top.toFloat()
        val stopX = decoratedBoundsWithMargins.right.toFloat()
        val stopY = startY
        c.drawLine(startX, startY, stopX, stopY, paint)
        c.restore()
    }

    override fun drawRightDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        c.save()
        val startX = decoratedBoundsWithMargins.right.toFloat()
        val startY = decoratedBoundsWithMargins.top.toFloat()
        val stopX = startX
        val stopY = decoratedBoundsWithMargins.bottom.toFloat()
        c.drawLine(startX, startY, stopX, stopY, paint)
        c.restore()
    }

    override fun drawBottomDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        c.save()
        val startX = decoratedBoundsWithMargins.left.toFloat()
        val startY = decoratedBoundsWithMargins.bottom.toFloat()
        val stopX = decoratedBoundsWithMargins.right.toFloat()
        val stopY = startY
        c.drawLine(startX, startY, stopX, stopY, paint)
        c.restore()
    }

}

class HorizontalDividerDrawer : DividerDrawer() {
    override fun drawLeftDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        TODO("Not yet implemented")
    }

    override fun drawTopDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        TODO("Not yet implemented")
    }

    override fun drawRightDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        TODO("Not yet implemented")
    }

    override fun drawBottomDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    ) {
        TODO("Not yet implemented")
    }

}

abstract class DividerDrawer {
    abstract fun drawLeftDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    )

    abstract fun drawTopDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    )

    abstract fun drawRightDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    )

    abstract fun drawBottomDivider(
        c: Canvas,
        paint: Paint,
        view: View,
        decoratedBoundsWithMargins: Rect
    )
}