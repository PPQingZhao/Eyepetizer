package com.pp.library_ui.model

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pp.library_ui.R


open class FollowCardItemViewModel<VH : ViewHolder>() {

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
    val drawableFolow = ObservableField<Int>(R.drawable.layer_follow)

    var adapter: RecyclerView.Adapter<VH>? = null

    open fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    fun onExpand(view: View) {
        expand.set(expand.get()?.not())
    }

    open fun onVideo(view: View) {}
}