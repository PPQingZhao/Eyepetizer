package com.pp.library_ui.model

import android.view.View


open class VideoCardItemViewModel(val data: Any?) {
    var title: String? = null
    var category: String? = null
    var imagePath: String? = null
    var icon: String? = null
    var duration: String? = null


    open fun onVideo(view: View) {}

    open fun onIcon(view:View) {}
}