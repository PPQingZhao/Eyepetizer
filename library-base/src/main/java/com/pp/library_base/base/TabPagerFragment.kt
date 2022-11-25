package com.pp.library_base.base

import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.helper.TabPagerFragmentHelper

abstract class TabPagerFragment<VB : ViewDataBinding, VM : ThemeViewModel> :
    ThemeFragment<VB, VM>() {
    val mHelper: TabPagerFragmentHelper by lazy {
        TabPagerFragmentHelper(
            this,
            getTabLayout(),
            getViewPager()
        )
    }

    abstract fun getTabLayout(): TabLayout

    abstract fun getViewPager(): ViewPager2

}