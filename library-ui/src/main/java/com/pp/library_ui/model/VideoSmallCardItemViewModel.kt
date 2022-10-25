package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField


open class VideoSmallCardItemViewModel(val data: Any?) {
    val title = ObservableField<String>()
    val category = ObservableField<String>()
    val imagePath = ObservableField<String>()
    val duration = ObservableField<String>()


    open fun onVideo(view: View) {}

}