package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.pp.library_ui.R
import com.pp.library_ui.adapter.MultiBindingAdapter


sealed class FollowCardItemViewModel {

    var area = ObservableField<String>()
    var content = ObservableField<String>()
    var icon = ObservableField<String>()
    var author = ObservableField<String>()
    var date = ObservableField<String>()
    var category = ObservableField<String>()
    val collectionCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val constraintDimensionRatio = ObservableField<String>()
    val drawableFollow = ObservableField<Int>(R.drawable.layer_follow)
//    val indicatorCount = ObservableInt()
//    var adapter: MultiBindingAdapter<*>? = null
//    val dataList = mutableListOf<Any>()

    var title = ObservableField<String>()

    open fun onItemClick(view: View) {}


    open class VideoFollowCardItemViewModel : FollowCardItemViewModel() {
        val cover = ObservableField<String?>()
        val playUrl = ObservableField<String?>()
    }

    open class ImageFollowCardItemViewModel : FollowCardItemViewModel() {
        var adapter: MultiBindingAdapter<*>? = null
        val dataList = mutableListOf<Any>()
        val indicatorCount = ObservableInt()
    }

}