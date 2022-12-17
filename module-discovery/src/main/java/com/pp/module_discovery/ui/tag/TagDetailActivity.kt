package com.pp.module_discovery.ui.tag

import android.os.Bundle
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
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.library_router_service.services.RouterPath
import com.pp.module_discovery.R
import com.pp.module_discovery.databinding.ActivityTagDetailBinding
import kotlin.math.abs

@Route(path = RouterPath.Discovery.activity_tag_detail)
class TagDetailActivity : ThemeActivity<ActivityTagDetailBinding, TagDetailViewModel>() {

    @JvmField
    @Autowired(name = "id")
    var id: String = ""

    val mHelper: TabPagerFragmentHelper by lazy {
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

    override val mBinding by lazy { ActivityTagDetailBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<TagDetailViewModel> {
        return TagDetailViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        mViewModel.getDetail(id)

        initToolbar()
        addObserver()
    }

    private fun initToolbar() {
        mBinding.appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            // toolbar 透明度跟随滚动变化
            val offset = abs(verticalOffset)
            mBinding.tagToolbar.alpha = offset * 1.0F / appBarLayout.totalScrollRange
        }
    }

    private fun addObserver() {
        mViewModel.tabInfoData.observe(this) {
            mHelper.attach(getPager(it))
        }
    }

    private fun getPager(tabInfo: TagDetailBean.TabInfo): TabPager {
        val factory = object : Pager.FragmentFactory {
            override fun create(position: Int): Fragment {
                return when (position) {
                    0 -> TagRecommendFragment(tabInfo.tabList.getOrNull(0))
                    1 -> TagSquareFragment(tabInfo.tabList.getOrNull(1))
                    else -> TagRecommendFragment(tabInfo.tabList.getOrNull(0))
                }
            }
        }

        val tabResources = mutableListOf<Int>()
        tabResources.add(R.string.tag_recommend)
        tabResources.add(R.string.tag_square)

        val tabPager = TabPager(tabResources.size, factory)
        tabPager.initTabs { position ->
            val tab = TabPager.Tab(null, 0, tabResources[position])
            tab
        }
        return tabPager;
    }
}