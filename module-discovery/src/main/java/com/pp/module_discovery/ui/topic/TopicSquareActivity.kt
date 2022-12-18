package com.pp.module_discovery.ui.topic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.ThemeActivity
import com.pp.library_base.base.helper.TabPagerFragmentHelper
import com.pp.library_network.eyepetizer.bean.nav.Nav
import com.pp.library_router_service.services.RouterPath
import com.pp.module_discovery.databinding.ActivityTopicSquareBinding

@Route(path = RouterPath.Discovery.activity_topic_square)
class TopicSquareActivity : ThemeActivity<ActivityTopicSquareBinding, TopicSquareViewModel>() {

    @JvmField
    @Autowired(name = "type")
    var type: String = ""

    override val mBinding by lazy { ActivityTopicSquareBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<TopicSquareViewModel> {
        return TopicSquareViewModel::class.java
    }

    private val mHelper: TabPagerFragmentHelper by lazy {
        TabPagerFragmentHelper(
            this,
            getTabLayout(),
            getViewPager()
        )
    }

    private fun getViewPager(): ViewPager2 {
        return mBinding.vp2
    }

    private fun getTabLayout(): TabLayout {
        return mBinding.tabLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        initToolbar()
        initData(type)
        addObserver()
    }

    private fun initData(type: String) {
        mViewModel.getTopicNav(type)
    }

    private fun initToolbar() {

    }

    private fun addObserver() {
        mViewModel.navTabList.observe(this) {
            mHelper.attach(getPager(it))
        }
    }

    private fun getPager(navList: List<Nav>): TabPager {
        val tabResources = mutableListOf<String>()
        navList.forEach {
            tabResources.add(it.title)
        }
        val factory = object : Pager.FragmentFactory {
            override fun create(position: Int): Fragment {
                val nav = navList.getOrNull(position)
                val pageLabel = nav?.pageLabel ?: ""
                val pageType = nav?.pageType ?: ""
                return TopicSquareFragment.newInstance(pageLabel, pageType)
            }
        }

        val tabPager = TabPager(tabResources.size, factory)
        tabPager.initTabs { position ->
            val tab = TabPager.Tab(null, 0, 0, tabResources[position])
            tab
        }
        return tabPager;
    }

}