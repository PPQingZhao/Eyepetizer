package com.pp.library_ui.databinding

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.library_ui.utils.load
import com.pp.library_ui.widget.ExpandableTextLayout
import com.pp.library_ui.widget.banner.BannerViewPager
import com.pp.library_ui.widget.banner.BaseBannerAdapter
import com.pp.library_ui.widget.gridlayout.CustomGridLayout
import com.pp.library_ui.widget.indicator.IndicatorView
import com.pp.library_ui.widget.videoviewer.GlobalVideoViewer
import com.pp.library_ui.widget.videoviewer.ItemVideoViewer

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:src")
    fun setImageResource(iv: ImageView, @DrawableRes res: Int) {
//        Log.e("TAG", "res: $res")
        iv.setImageResource(res)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:bindSrc")
    fun setImageResource(imageView: ImageView, path: String?) {
//        Log.e("TAG","path: $path")
        imageView.load(path)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("mVisibility")
    fun setVisibility(v: View, visibility: Boolean) {
        v.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("onPageCallback")
    fun <T> setOnPageCallback(
        bannerViewPager: BannerViewPager<T>,
        callback: ViewPager2.OnPageChangeCallback,
    ) {
        bannerViewPager.setOnPageChangedCallback(callback)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setInitIndicator")
    fun <T> setInitIndicator(
        bannerViewPager: BannerViewPager<T>,
        indicatorView: IndicatorView,
    ) {
        bannerViewPager.setOnPageChangedCallback(indicatorView.onPageChangeCallback as OnPageChangeCallback)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setInitIndicator")
    fun <T> setInitIndicator(
        pager: ViewPager2,
        indicatorView: IndicatorView,
    ) {
        pager.registerOnPageChangeCallback(indicatorView.onPageChangeCallback as OnPageChangeCallback)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter(
        value = ["initIndicator", "autoVisibility"],
        requireAll = true
    )
    fun initIndicator(
        indicatorView: IndicatorView,
        count: Int,
        autoVisibility: Boolean,
    ) {
        indicatorView.initIndicator(count)
        indicatorView.visibility = if (autoVisibility && count > 1) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setCurrentItem", requireAll = false)
    fun setCurrentItem(vp2: ViewPager2, currentItem: Int) {
        vp2.currentItem = currentItem
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter(value = ["singleAdapter", "dataList"], requireAll = true)
  fun  setSingleAdapter(
        vp2: ViewPager2,
        adapter: MultiBindingAdapter<*>,
        dataList: List<*>,
    ) {
        if (vp2.adapter !is MultiBindingAdapter<*>) {
            vp2.adapter = adapter
        }

        if (vp2.adapter is MultiBindingAdapter<*>) {
            (vp2.adapter as MultiBindingAdapter<*>).setDataList(dataList as List<Nothing>)
        }

    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setBannerAdapter", requireAll = false)
    fun <T, VH : RecyclerView.ViewHolder> setBannerAdapter(
        bannerViewPager: BannerViewPager<T>,
        adapter: BaseBannerAdapter<T, VH>,
    ) {
        bannerViewPager.setAdapter(adapter)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter(
        value = ["constraintLayout", "dimensionRatio"],
        requireAll = true
    )
    fun setConstraintDimensionRatio(view: View, cl: ConstraintLayout, dimension: String) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(cl)
        constraintSet.setDimensionRatio(view.id, dimension)
        constraintSet.applyTo(cl)
    }

    @SuppressLint("SetJavaScriptEnabled")
    @JvmStatic
    @androidx.databinding.BindingAdapter("loadUrl", requireAll = false)
    fun loadUrl(webView: WebView, url: String) {
        if (!webView.originalUrl.equals(url)) {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url)
        }
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("onTouch", requireAll = false)
    fun onTouch(v: View, l: View.OnTouchListener) {
        v.setOnTouchListener(l)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setPageOffsetLimit", requireAll = false)
    fun setPageOffsetLimit(vp2: ViewPager2, limit: Int) {
        vp2.offscreenPageLimit = limit
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setPageTransform", requireAll = false)
    fun setPageTransform(vp2: ViewPager2, transform: ViewPager2.PageTransformer) {
        vp2.setPageTransformer(transform)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:background")
    fun setBackground(view: View, @ColorInt color: Int) {
        view.setBackgroundColor(color)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("nestedScrollingEnabled")
    fun setNestedScrollingEnabled(view: ViewPager2, enable: Boolean) {
        view.getChildAt(0).isNestedScrollingEnabled = enable
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter(value = ["startGlobalPlay", "setCover"], requireAll = true)
    fun startGlobalPlay(video: GlobalVideoViewer, playUrl: String?, cover: String?) {
        video.setPlayUrlAndCover(playUrl ?: "", cover ?: "")
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter(value = ["autoPlay"], requireAll = false)
    fun startAutoPlay(video: ItemVideoViewer, autoPlay: Boolean) {
        video.setAutoPlay(autoPlay)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:text")
    fun setExpandText(view: ExpandableTextLayout, text: String?) {
        view.setContent(text)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:textColor")
    fun setExpandContentColor(view: ExpandableTextLayout, color: Int) {
        view.setExpandTextColor(color)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("expand")
    fun setExpand(view: ExpandableTextLayout, expand: Boolean) {
        view.isExpand = expand
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter(value = ["android:text", "expand"], requireAll = true)
    fun setExpandTextAndExpand(view: ExpandableTextLayout, text: String, expand: Boolean) {
        view.setContent(text)
        view.isExpand = expand
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setUrls", requireAll = true)
    fun setGridUrls(view: CustomGridLayout, urls: List<String>) {
        view.setUrls(urls)
    }
}