package com.pp.module_video_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.library_network.eyepetizer.bean.ItemDetailsBean
import com.pp.library_ui.R
import com.pp.module_video_details.databinding.FragmentDetailsBinding

class DetailsFragment(details: ItemDetailsBean?) :
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
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