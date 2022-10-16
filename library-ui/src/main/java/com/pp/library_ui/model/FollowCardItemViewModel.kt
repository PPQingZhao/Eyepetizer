package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


open class FollowCardItemViewModel<VH : ViewHolder>() {

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

    var adapter: RecyclerView.Adapter<VH>? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    fun onExpand(view: View) {
        expand.set(expand.get()?.not())
    }
}