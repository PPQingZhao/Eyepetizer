package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField


open class FollowCardItemViewModel<T>(val item: T?) {

    var content: String? = ""
    var icon: String? = ""
    var author: String? = ""
    var date: String? = ""
    var feed: String? = ""
    var category: String? = ""
    val expand: ObservableField<Boolean> = ObservableField(false)
    val collectionCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()


    fun onExpand(view: View) {
        expand.set(expand.get()?.not())
    }
}