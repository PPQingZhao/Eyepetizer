package com.pp.library_ui.model

import android.content.Context
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pp.library_ui.R
import com.pp.library_ui.adapter.MultiBindingAdapter

abstract class ItemRecyclerViewModel {
    val bg = ObservableInt(R.drawable.shape_rectangle_white)
    open var adapter: MultiBindingAdapter<Any>? = null

    abstract fun getLayoutManager(context: Context): RecyclerView.LayoutManager
}