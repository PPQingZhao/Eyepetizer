package com.pp.module_community

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridDivider(private val space: Int = 20, private val column: Int = 2,val canDraw:(pos:Int)->Boolean) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        view.setTag(position)
        if (position == RecyclerView.NO_POSITION){
            return
        }

        if(!canDraw.invoke(position)){
            return
        }
        val layoutManager = parent.layoutManager

        if (layoutManager is GridLayoutManager) {
            val spanSizeLookup = layoutManager.spanSizeLookup
            val spanCount = layoutManager.spanCount

            val spanSize = spanSizeLookup.getSpanSize(position)
            val spanIndex = spanSizeLookup.getSpanIndex(position, spanCount)
            val spanGroupIndex = spanSizeLookup.getSpanGroupIndex(position, spanCount)

            if (spanSize < spanCount) {
                if (spanIndex < spanSize) {
                    outRect.left = space
                    outRect.right = space / 2
                } else {
                    outRect.left = space / 2
                    outRect.right = space
                }
            } else {
                outRect.left = space
                outRect.right = space
            }

            if (spanGroupIndex != 0) {
                outRect.top = space
            }
        }
    }
}