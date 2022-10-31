package com.pp.library_ui.model

import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_ui.widget.banner.BaseBannerAdapter

open class BannerListItemViewModel<T, VH : ViewHolder> {
    val index = ObservableInt()
    var bannerAdapter: BaseBannerAdapter<T, VH>? = null

    var onPageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                index.set(position + 1)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        }
}