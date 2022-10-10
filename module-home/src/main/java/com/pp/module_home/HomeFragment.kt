package com.pp.module_home

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.library_servicemanager.services.RouterPath
import com.pp.module_home.databinding.FragmentHomeBinding

@Route(path = RouterPath.Home.fragment_home)
class HomeFragment : TabPagerFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getTabLayout(): TabLayout {
        return mBinding.homeTabLayout
    }

    override fun getViewPager(): ViewPager2 {
        return mBinding.homeViewpager
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun getModelClazz(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onFirstResume() {
        mHelper.attach(getPager())
    }

    fun getPager(): TabPager {
        val factory = object : Pager.FragmentFactory {
            override fun create(position: Int): Fragment {
                return when (position) {
                    0 -> RecommendFragment()
                    1 -> FollowFragment()
                    2 -> DailyFragment()
                    else -> FollowFragment()
                }
            }

        }
        val tabResources = arrayOf(
            R.string.home_tab_recommend,
            R.string.home_tab_follow,
            R.string.home_tab_daily
        )
        val tabPager = TabPager(tabResources.size, factory)
        tabPager.initTabs { position ->
            val tab = TabPager.Tab(null, 0, tabResources[position])
            tab
        }
        return tabPager;
    }
}