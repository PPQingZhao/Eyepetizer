package com.pp.module_video_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.module_video_details.databinding.FragmentDetailsBinding
import com.pp.mvvm.LifecycleViewModel
import com.pp.library_ui.R

class DetailsFragment : TabPagerFragment<FragmentDetailsBinding, LifecycleViewModel>() {
    override val mBinding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }
    override fun getModelClazz(): Class<LifecycleViewModel> {
        return LifecycleViewModel::class.java
    }

    override fun getTabLayout(): TabLayout {
        return mBinding.detailsTabLayout
    }

    override fun getViewPager(): ViewPager2 {
        return mBinding.detailsViewpager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHelper.attach(getPager())
    }

    private fun getPager(): TabPager {
        val factory = object : Pager.FragmentFactory {
            override fun create(position: Int): Fragment {
                return when (position) {
                    0 -> IntroductionFragment()
                    else -> CommentsFragment()
                }
            }
        }
        val tabResources = arrayOf(R.string.introduction, R.string.comments)
        val tabPager = TabPager(tabResources.size, factory)
        tabPager.initTabs { position ->
            val tab = TabPager.Tab(null, 0, tabResources[position])
            tab
        }

        return tabPager
    }
}