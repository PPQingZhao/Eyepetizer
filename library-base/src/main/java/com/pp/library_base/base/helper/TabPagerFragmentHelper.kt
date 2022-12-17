package com.pp.library_base.base.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pp.library_base.base.TabPager

class TabPagerFragmentHelper(
    val tabLayout: TabLayout, manager: FragmentManager,
    lifecycle: Lifecycle,
    viewPager: ViewPager2
) {
    private lateinit var pager: TabPager
    private val pagerHelper: PagerFragmentHelper

    init {
        pagerHelper = PagerFragmentHelper(manager, lifecycle, viewPager)
    }

    constructor(
        activity: FragmentActivity,
        taLayout: TabLayout,
        viewPager: ViewPager2
    ) : this(taLayout, activity.supportFragmentManager, activity.lifecycle, viewPager)

    constructor(
        fragment: Fragment,
        taLayout: TabLayout,
        viewPager: ViewPager2
    ) : this(taLayout, fragment.childFragmentManager, fragment.lifecycle, viewPager)

    fun attach(tabPager: TabPager, smoothScroll: Boolean) {
        this.pager = tabPager;
        pagerHelper.attach(tabPager)

        //TabLayout联动ViewPager
//        tabLayout.clearOnTabSelectedListeners()
        tabLayout.removeAllTabs()

        TabLayoutMediator(
            tabLayout,
            pagerHelper.viewPager,
            true,
            smoothScroll,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val pTab = pager.getTab(position)
                tab.customView = pTab.tab
                if (pTab.icon > 0) {
                    tab.setIcon(pTab.icon)
                }
                if (pTab.text > 0) {
                    tab.setText(pTab.text)
                }
                if (pTab.title.isNotEmpty()) {
                    tab.text = pTab.title
                }

            }).attach()
    }

    fun attach(tabPager: TabPager) {
        attach(tabPager, true)
    }
}