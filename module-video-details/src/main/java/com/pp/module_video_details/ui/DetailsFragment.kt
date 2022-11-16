package com.pp.module_video_details.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.R
import com.pp.module_video_details.databinding.FragmentDetailsBinding

class DetailsFragment(details: MetroDataBean?) :
    TabPagerFragment<FragmentDetailsBinding, DetailsViewModel>() {
    override val mBinding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }
    override fun getModelClazz(): Class<DetailsViewModel> {
        return DetailsViewModel::class.java
    }

    private val modelFactory: ViewModelProvider.Factory

    init {
        modelFactory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    DetailsViewModel::class.java
                    -> DetailsViewModel(details, activity?.application!!) as T

                    else -> super@DetailsFragment.getModelFactory().create(modelClass)
                }
            }

        }
    }

    override fun getModelFactory(): ViewModelProvider.Factory = modelFactory

    constructor() : this(null)

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
                    0 -> {
                        IntroductionFragment(mViewModel.details)
                    }
                    else -> ARouter.getInstance()
                        .build(RouterPath.Comments.fragment_comments)
                        .withInt("resourceId", mViewModel.details?.resourceId?.toInt() ?: 0)
                        .withString("resourceType", mViewModel.details?.resourceType)
                        .navigation() as Fragment
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