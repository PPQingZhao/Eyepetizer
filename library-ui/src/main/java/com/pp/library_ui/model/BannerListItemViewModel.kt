package com.pp.library_ui.model

import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_ui.widget.banner.BaseBannerAdapter

open class BannerListItemViewModel<T, VH : ViewHolder> {
    val indicatorCount = ObservableInt()
    var bannerAdapter: BaseBannerAdapter<T, VH>? = null

}