package com.pp.library_base.base

import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.utils.fragmenthelper.TabPagerFragmentHelper
import com.pp.mvvm.LifecycleFragment
import com.pp.mvvm.LifecycleViewModel

abstract class TabPagerFragment<VB : ViewDataBinding, VM : LifecycleViewModel> :
    LifecycleFragment<VB, VM>() {
    val mHelper: TabPagerFragmentHelper by lazy { TabPagerFragmentHelper(this,getTabLayout(),getViewPager()) }

    abstract fun getTabLayout(): TabLayout

    abstract fun getViewPager(): ViewPager2

}