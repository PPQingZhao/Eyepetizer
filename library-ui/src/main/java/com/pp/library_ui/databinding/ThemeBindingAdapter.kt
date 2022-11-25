package com.pp.library_ui.databinding

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.pp.library_ui.widget.indicator.IndicatorView

object ThemeBindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("tabIndicatorSelectedColor")
    fun setSelectedTabIndicatorColor(tabLayout: TabLayout, @ColorInt color: Int) {
        tabLayout.setSelectedTabIndicatorColor(color)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter(
        value = ["tabNormalTextColor", "tabSelectedTextColor"],
        requireAll = true
    )
    fun setTabTextColor(
        tabLayout: TabLayout,
        @ColorInt colorNormal: Int,
        @ColorInt colorSelected: Int
    ) {
        tabLayout.setTabTextColors(colorNormal, colorSelected)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("tint")
    fun setImageTint(
        view: ImageView,
        @ColorInt color: Int,
    ) {
        view.imageTintList = ColorStateList.valueOf(color)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:textColor")
    fun setTextColor(
        view: TextView,
        @ColorInt color: Int,
    ) {
        view.setTextColor(color)
    }

    @SuppressLint("RestrictedApi")
    @JvmStatic
    @androidx.databinding.BindingAdapter("android:drawableTint")
    fun setDrawableTint(
        view: AppCompatTextView,
        @ColorInt color: Int,
    ) {
        view.supportCompoundDrawablesTintList = ColorStateList.valueOf(color)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("indicatorNormalColor")
    fun setIndicatorNormalColor(
        view: IndicatorView,
        @ColorInt color: Int,
    ) {
        view.normalColor = color
        view.invalidate()
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("indicatorSelectedColor")
    fun setIndicatorSelectedColor(
        view: IndicatorView,
        @ColorInt color: Int,
    ) {
        view.selectedColor = color
        view.invalidate()
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("contentScrim")
    fun setContentScrimColor(
        view: CollapsingToolbarLayout,
        @ColorInt color: Int,
    ) {
        view.contentScrim = ColorDrawable(color)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("statusBarScrim")
    fun setStatusBarScrimColor(
        view: CollapsingToolbarLayout,
        @ColorInt color: Int,
    ) {
        view.statusBarScrim = ColorDrawable(color)
    }

}