package com.pp.library_ui.widget.layoutmanager

import android.annotation.SuppressLint
import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt


class CardLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)

        detachAndScrapAttachedViews(recycler)

        val bottomPos = if (itemCount > CardConfig.DEFAULT_SHOW_ITEM) 1 else 0

        for (i in bottomPos until itemCount) {
            val view = recycler.getViewForPosition(i)

            addView(view)
            measureChild(view, 0, 0)
            val gapWidth = width - getDecoratedMeasuredWidth(view)
            val gapHeight = height - getDecoratedMeasuredHeight(view)
            layoutDecoratedWithMargins(
                view,
                0,
                gapHeight / 2,
                getDecoratedMeasuredWidth(view),
                gapHeight / 2 + getDecoratedMeasuredHeight(view)
            )

            val level = itemCount - i - 1
            if (level >= 0) {
                if (CardConfig.DEFAULT_SHOW_ITEM - level > 1) {
                    view.translationX = level * CardConfig.DEFAULT_TRANSLATE_X
                    view.scaleX = (1 - level * CardConfig.DEFAULT_SCALE)
                    view.scaleY = (1 - level * CardConfig.DEFAULT_SCALE)
                } else {
                    view.translationX = (level - 1) * CardConfig.DEFAULT_TRANSLATE_X
                    view.scaleX = (1 - (level - 1) * CardConfig.DEFAULT_SCALE)
                    view.scaleY = (1 - (level - 1) * CardConfig.DEFAULT_SCALE)
                }
            }
        }

    }

    class CardItemTouchHelperCallback<T>(
        private val mAdapter: RecyclerView.Adapter<*>,
        private val mData: MutableList<T>,
    ) : ItemTouchHelper.SimpleCallback(1, 15) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //viewHolder.itemView.setOnTouchListener(null)

            val layoutPos = viewHolder.layoutPosition
            val remove = mData.removeAt(layoutPos)
            mData.add(0, remove)
            mAdapter.notifyDataSetChanged()
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            val maxDistance = recyclerView.width / 2
            val moveDistance = sqrt(dX * dX + dY * dY)
            var percent = moveDistance / maxDistance

            if (percent > 1f) {
                percent = 1f
            }

            for (item in 0 until recyclerView.childCount) {
                val child = recyclerView.getChildAt(item)
                val level = CardConfig.DEFAULT_SHOW_ITEM - item - 1
                if (level > 0) {
                    if (CardConfig.DEFAULT_SHOW_ITEM - level > 1) {
                        child.translationX =
                            level * CardConfig.DEFAULT_TRANSLATE_X - percent * CardConfig.DEFAULT_TRANSLATE_X
                        child.scaleX =
                            1 - level * CardConfig.DEFAULT_SCALE + percent * CardConfig.DEFAULT_SCALE
                        child.scaleY =
                            1 - level * CardConfig.DEFAULT_SCALE + percent * CardConfig.DEFAULT_SCALE
                    }
                }
            }
        }

        override fun getAnimationDuration(
            recyclerView: RecyclerView,
            animationType: Int,
            animateDx: Float,
            animateDy: Float
        ): Long {
            return 200
        }

    }

    object CardConfig {
        const val DEFAULT_SHOW_ITEM = 3
        const val DEFAULT_SCALE = 0.1f
        const val DEFAULT_TRANSLATE_X = 80f
    }
}