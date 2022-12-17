package com.pp.library_ui.widget.refresh

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.view.NestedScrollingParent3
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat
import androidx.core.widget.ListViewCompat

class PullRefreshLayout : ViewGroup, NestedScrollingParent3 {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    private val mNestedScrollingParentHelper: NestedScrollingParentHelper =
        NestedScrollingParentHelper(this)
    private var mTarget: View? = null
    private var mInitialDownY = 0f
    private var mCurOffsetY = 0f
    var maxOffsetY = 64f
        set(value) {
            field = value
        }

    var onChildScrollUpCallback: OnChildScrollUpCallback? = null

    companion object {
        const val INVALID_POINTER = -1
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {

        maxOffsetY = 64 * context.resources.displayMetrics.density
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        ensureTarget()
        if (null == mTarget) {
            return
        }

        mTarget!!.measure(
            MeasureSpec.makeMeasureSpec(
                measuredWidth - paddingLeft - paddingRight,
                MeasureSpec.EXACTLY
            ),
            MeasureSpec.makeMeasureSpec(
                measuredHeight - paddingTop - paddingBottom,
                MeasureSpec.EXACTLY
            )
        )
    }

    private fun ensureTarget() {
        if (childCount == 0) {
            return
        }
        val target = getChildAt(0)
        if (mTarget == target) {
            return
        }
        mTarget = target
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        ensureTarget()
        if (null == mTarget) {
            return
        }

        val width = measuredWidth
        val height = measuredHeight
        val child = mTarget!!
        val childLeft = paddingLeft
        val childTop = paddingTop
        val childWidth = width - paddingLeft - paddingRight
        val childHeight = height - paddingTop - paddingBottom
        child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (null == ev) {
            return false
        }

        val actionMasked = ev.actionMasked

        when (actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mActivePointerId = ev.getPointerId(0)

                val findPointerIndex = ev.findPointerIndex(mActivePointerId)
                if (findPointerIndex < 0) {
                    return false
                }

                val y = ev.getY(findPointerIndex)
                mInitialDownY = y
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_CANCEL -> {
                mActivePointerId = INVALID_POINTER
            }
            else -> {

            }
        }
        return true
    }

    private var mActivePointerId = -1

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (null == event) {
            return false
        }

        val actionMasked = event.actionMasked

        if (!isEnabled || !canChildScrollUp()) {
            return false
        }

        when (actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mActivePointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                val pointerIndex = event.findPointerIndex(mActivePointerId)
                if (pointerIndex < 0) {
                    return false
                }

                val y = event.getY(pointerIndex)

                var curOffsetY = mCurOffsetY
                val offsetY = y - curOffsetY - mInitialDownY
                curOffsetY += offsetY*0.8f
                if (curOffsetY <= maxOffsetY) {
                    Log.e("TAG", "offsetY: ${offsetY}")
                    ViewCompat.offsetTopAndBottom(mTarget!!, offsetY.toInt())
                    mCurOffsetY = curOffsetY
                }
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                if (actionIndex < 0) {
                    return false
                }

                mActivePointerId = event.getPointerId(actionIndex)
            }
            MotionEvent.ACTION_UP -> {
                mActivePointerId = INVALID_POINTER
                mCurOffsetY = 0f
            }
            else -> {

            }
        }
        return true
    }

    private fun canChildScrollUp(): Boolean {
        if (null == mTarget) {
            return false
        }

        if (null != onChildScrollUpCallback) {
            return onChildScrollUpCallback!!.canChildScrollUp(this, mTarget!!)
        }

        if (mTarget is ListView) {
            return ListViewCompat.canScrollList(mTarget as ListView, -1)
        }

        return mTarget!!.canScrollVertically(-1)
    }

    interface OnChildScrollUpCallback {
        fun canChildScrollUp(parent: PullRefreshLayout, child: View): Boolean
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return false
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        mNestedScrollingParentHelper.onStopNestedScroll(target, type)
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        TODO("Not yet implemented")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        TODO("Not yet implemented")
    }
}