package com.pp.library_ui.model

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
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
    val collectionCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val constraintDimensionRatio = ObservableField<String>()
    val drawableFollow = ObservableField<Int>(R.drawable.layer_follow)
    val indicatorCount = ObservableInt()
    var adapter: RecyclerView.Adapter<VH>? = null

    var title = ObservableField<String>()

    open fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    open fun onVideo(view: View) {}
}