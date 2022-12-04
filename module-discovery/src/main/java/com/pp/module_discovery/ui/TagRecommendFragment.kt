package com.pp.module_discovery.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.model.MetroBannerItemViewModel
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.module_discovery.databinding.FragmentTagRecommendBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TagRecommendFragment(tab: TagDetailBean.TabInfo.Tab?) : ThemeFragment<FragmentTagRecommendBinding, TagRecommendViewModel>() {
    override val mBinding by lazy {
        FragmentTagRecommendBinding.inflate(layoutInflater)
    }
    override fun getModelClazz(): Class<TagRecommendViewModel> {
        return TagRecommendViewModel::class.java
    }

    private var url = ""

    init {
        url = tab?.apiUrl ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.recycler.layoutManager = LinearLayoutManager(requireContext())

        mBinding.recycler.adapter = mAdapter
    }

    private val mAdapter: MultiBindingPagingDataAdapter<VideoBean.Item> by lazy {
        val call = object : DiffUtil.ItemCallback<VideoBean.Item>() {
            override fun areItemsTheSame(oldItem: VideoBean.Item, newItem: VideoBean.Item): Boolean {
                val result = oldItem.id == newItem.id
                return result
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: VideoBean.Item, newItem: VideoBean.Item): Boolean {
                val result = oldItem.id == newItem.id
                return result
            }
        }

        val type_picture_follow = 1
        val adapter = MultiBindingPagingDataAdapter(call)

        /*adapter.addBindingItem(DefaultViewBindingItem<VideoBean.Item>(
                type_picture_follow,
                { it?.type == EyepetizerService.ItemType.pictureFollowCard },
                { com.pp.library_ui.databinding.ItemFollowCardBinding.inflate(layoutInflater, it, false) },
                { binding, item, cacheItemViewModel ->
                    if (cacheItemViewModel is MetroBannerItemViewModel) {
                        cacheItemViewModel.metroList = item?.data?.cardData?.body?.metroList
                        cacheItemViewModel
                    } else MetroBannerItemViewModel(metroList = item?.data?.cardData?.body?.metroList)
                })*/
        adapter
    }

    override fun onFirstResume() {
        super.onFirstResume()

        try {
            lifecycleScope.launch {
                mViewModel.getVideoPage(url).collect {
                    //mAdapter.submitData(it)
                }
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}