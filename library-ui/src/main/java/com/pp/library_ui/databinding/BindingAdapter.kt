package com.pp.library_ui.databinding

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.utils.ImageLoader

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:src")
    fun setImageResource(iv: ImageView, @DrawableRes res: Int) {
        Log.e("TAG", "res: $res")
        iv.setImageResource(res)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:bindSrc", requireAll = false)
    fun setImageResource(imageView: ImageView, path: String?) {
//        Log.e("TAG","path: $path")
        ImageLoader.load(imageView, path)
    }


    @androidx.databinding.BindingAdapter("adapter")
    @JvmStatic
    fun setAdapter(recyclerView: RecyclerView, adapter: Adapter<BindingHolder<ViewDataBinding>>?) {
//        Log.e("BindingAdapter", "setAdapter")
        recyclerView.adapter = adapter
    }

    @androidx.databinding.BindingAdapter("rvLayoutManager")
    @JvmStatic
    fun setLayoutManager(recyclerView: RecyclerView, manager: LayoutManager?) {
//        Log.e("BindingAdapter", "setLayoutManager")
        recyclerView.layoutManager = manager
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("mVisibility")
    fun setVisibility(v: View, visibility: Boolean) {
        v.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}