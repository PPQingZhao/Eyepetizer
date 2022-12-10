package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ObservableLong
import com.pp.library_ui.R


open class FollowCardItem2ViewModel {

    var isVideo: Boolean = true
    var area = ObservableField<String>()
    var content = ObservableField<String>()
    var icon = ObservableField<String>()
    var author = ObservableField<String>()
    var date = ObservableField<String>()
    var cover = ObservableField<String>()
    var category = ObservableField<String>()
    val expand: ObservableField<Boolean> = ObservableField(false)
    val collectionCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val constraintDimensionRatio = ObservableField<String>()
    val drawableFollow = ObservableField<Int>(R.drawable.layer_follow)
    val indicatorCount = ObservableInt()
    val duration = ObservableField<String>()

    var title = ObservableField<String>()

    open fun onVideo(view: View) {}
}