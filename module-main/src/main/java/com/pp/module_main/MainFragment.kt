package com.pp.module_main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.library_router_service.services.RouterPath
import com.pp.module_main.databinding.FragmentMainBinding
import com.pp.module_main.databinding.ViewTabBinding

class MainFragment : TabPagerFragment<FragmentMainBinding, MainViewModel>() {
    override val mBinding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }

    override fun getTabLayout(): TabLayout {
        return mBinding.mainTabLayout
    }

    override fun getViewPager(): ViewPager2 {
        mBinding.mainViewpager.isUserInputEnabled = false
//        mBinding.mainViewpager.offscreenPageLimit = 1
        return mBinding.mainViewpager
    }

    override fun getModelClazz(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    override fun onFirstResume() {
        mHelper.attach(getPager(), false)
    }

    fun getPager(): TabPager {
        val factory = object : Pager.FragmentFactory {
            override fun create(position: Int): Fragment {
                return when (position) {
                    0 -> ARouter.getInstance().build(RouterPath.Home.fragment_home)
                        .navigation() as Fragment
                    1 -> ARouter.getInstance().build(RouterPath.Community.fragment_community)
                        .navigation() as Fragment
                    3->{
                        ARouter.getInstance().build(RouterPath.User.fragment_user)
                            .navigation() as Fragment
                    }
                    else -> TestFragment()
                }
            }
        }
        // tab 资源
        val resources = arrayListOf<Pair<Int, Int>>(
            R.drawable.selector_home_page to R.string.main_tab_pager,
            R.drawable.selector_community to R.string.main_tab_community,
            R.drawable.selector_notification to R.string.main_tab_notification,
            R.drawable.selector_mine to R.string.main_tab_mine
        )
        // 创建 pager
        val tabPager = TabPager(resources.size, factory)
        // 创建 tab
        tabPager.initTabs { position ->
            val tabBinding = DataBindingUtil.inflate<ViewTabBinding>(
                layoutInflater,
                R.layout.view_tab,
                null,
                false
            )
            val resourcePair = resources[position]
            val tab =
                TabPager.Tab(tabBinding.root, resourcePair.first, resourcePair.second);
            tabBinding.viewModel = tab
            tab
        }
        return tabPager
    }


}