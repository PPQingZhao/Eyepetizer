package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField

open class DetailsInfoViewModel {

    val icon = ObservableField<String>()
    val author = ObservableField<String>()
    val publish = ObservableField<String>()
    val content = ObservableField<String>()
    val collectionCount = ObservableField<String>("0")
    val realCollectionCount = ObservableField<String>("0")
    val replyCount = ObservableField<String>("0")
    val tag = ObservableField<String>()

    fun onIcon(view: View) {}
    fun onFollow(view: View) {}
    fun onMessage(view: View) {}
    fun onCollection(view: View) {}
    fun onRealCollection(view: View) {}
    fun onReply(view: View) {}
    fun onShare(view: View) {}
}