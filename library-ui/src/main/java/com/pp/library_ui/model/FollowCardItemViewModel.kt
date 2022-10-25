package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


open class FollowCardItemViewModel<VH : ViewHolder>() {

    var videoType: Boolean = false
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

    var adapter: RecyclerView.Adapter<VH>? = null

    fun onExpand(view: View) {
        expand.set(expand.get()?.not())
    }
}