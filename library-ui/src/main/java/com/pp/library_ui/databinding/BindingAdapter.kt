package com.pp.library_ui.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_ui.utils.ExpandTextWatcher
import com.pp.library_ui.utils.ImageLoader
import com.pp.library_ui.widget.banner.BannerViewPager
import com.pp.library_ui.widget.banner.BaseBannerAdapter

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:src")
    fun setImageResource(iv: ImageView, @DrawableRes res: Int) {
//        Log.e("TAG", "res: $res")
        iv.setImageResource(res)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:bindSrc", requireAll = false)
    fun setImageResource(imageView: ImageView, path: String?) {
//        Log.e("TAG","path: $path")
        ImageLoader.load(imageView, path)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("mVisibility")
    fun setVisibility(v: View, visibility: Boolean) {
        v.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:expandText")
    fun expandText(textView: TextView, expandText: String) {

        // todo
        textView.addTextChangedListener(ExpandTextWatcher(textView, expandText,"收起"))

    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("onPageCallback", requireAll = false)
    fun <T> setOnPageCallback(
        bannerViewPager: BannerViewPager<T>,
        callback: ViewPager2.OnPageChangeCallback
    ) {
        bannerViewPager.setOnPageChangedCallback(callback)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setCurrentItem", requireAll = false)
    fun setCurrentItem(vp2: ViewPager2, currentItem: Int) {
        vp2.currentItem = currentItem
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setBannerAdapter", requireAll = false)
    fun <T, VH : RecyclerView.ViewHolder> setBannerAdapter(
        bannerViewPager: BannerViewPager<T>,
        adapter: BaseBannerAdapter<T, VH>
    ) {
        bannerViewPager.setAdapter(adapter)
    }

}