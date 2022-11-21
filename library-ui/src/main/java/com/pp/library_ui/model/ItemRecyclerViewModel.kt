package com.pp.library_ui.model

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_ui.R
import com.pp.library_ui.adapter.MultiBindingAdapter

abstract class ItemRecyclerViewModel {
    val bg = ObservableInt(R.drawable.shape_rectangle_white)
    val dimensionRatio = ObservableField<String>("4:3")
    val limit = ObservableInt(3)
    open var adapter: MultiBindingAdapter<Any>? = null

    abstract fun getLayoutManager(context: Context): RecyclerView.LayoutManager

    private val mScaleOffset = 200f
    private val mTranslationOffset = 100f
    val transform = object: ViewPager2.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            if (position <= 0f) {
                page.translationX = 0f
            } else {
                val pageWidth = page.width
                val transX = -pageWidth * position + mTranslationOffset
                page.translationX = transX

                val scale = (pageWidth - mScaleOffset * position) / pageWidth.toFloat()
                page.scaleX = scale
                page.scaleY = scale

                page.translationZ = -position
            }
        }
    }
}