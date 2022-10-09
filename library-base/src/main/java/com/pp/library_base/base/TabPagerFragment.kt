package com.pp.library_base.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pp.mvvm.LifecycleViewModel

abstract class TabPagerFragment<VB : ViewDataBinding, VM : LifecycleViewModel> :
    ViewPagerFragment<VB, VM>() {
    val mTabLayout: TabLayout by lazy { getTabLayout() }

    abstract fun getTabLayout(): TabLayout

    abstract override fun getPager(): TabPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTabLayout();
    }

    /**
     * TabLayout联动ViewPager
     */
    private fun setupTabLayout() {
        mTabLayout.clearOnTabSelectedListeners()
        mTabLayout.removeAllTabs()

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewPager.setCurrentItem(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        TabLayoutMediator(
            mTabLayout,
            mViewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val pTab = getPager().getTab(position)
                tab.customView = pTab.tab
                if (pTab.icon > 0) {
                    tab.setIcon(pTab.icon)
                }
                if (pTab.text > 0) {
                    tab.setText(pTab.text)
                }

            }).attach()
    }
}