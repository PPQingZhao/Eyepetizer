package com.pp.library_ui.model

import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField

open class WebViewModel {

    val url = ObservableField<String>()

    val onTouchListener = object: View.OnTouchListener {
        override fun onTouch(v: View, e: MotionEvent): Boolean {
            val parent = v.parent as ViewGroup
            parent.requestDisallowInterceptTouchEvent(true)
            return false
        }
    }

}