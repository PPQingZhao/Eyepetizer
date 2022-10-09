package com.pp.library_ui.databinding

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter(value = ["android:src"])
    fun setImageResource(iv: ImageView, @DrawableRes res: Int) {
        iv.setImageResource(res)
    }
}