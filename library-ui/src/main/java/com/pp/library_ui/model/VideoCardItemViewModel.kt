package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField


open class VideoCardItemViewModel() {
    val title = ObservableField<String>()
    val category = ObservableField<String>()
    val imagePath = ObservableField<String>()
    val playUrl = ObservableField<String>()
    val enablePlay = ObservableField<Boolean>()
    val icon = ObservableField<String>()
    val duration = ObservableField<String>()


    open fun onVideo(view: View) {}

    open fun onIcon(view: View) {}
}