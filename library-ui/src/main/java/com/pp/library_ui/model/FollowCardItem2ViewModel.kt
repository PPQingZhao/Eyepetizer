package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.pp.library_ui.R
import java.text.SimpleDateFormat


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

    val urls = ObservableField<List<String>>()
    val tag1 = ObservableField("")
    val tag2 = ObservableField("")
    val tag3 = ObservableField("")

    var title = ObservableField<String>()

    val simpleFormat by lazy { SimpleDateFormat("yyyy/MM/dd") }

    open fun onVideo(view: View) {}
}