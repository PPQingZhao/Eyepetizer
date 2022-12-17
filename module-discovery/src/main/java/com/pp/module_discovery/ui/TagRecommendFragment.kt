package com.pp.module_discovery.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.VideoPagingDataAdapterType
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.library_network.eyepetizer.bean.detail.VideoBean
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

    private val mAdapter: MultiBindingPagingDataAdapter<Item> by lazy {
        val call = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                val result = oldItem.id == newItem.id
                return result
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                val result = oldItem.id == newItem.id
                return result
            }
        }

        val type_text_card = 1
        val type_follow_card = type_text_card + 1
        val type_video_small_card = type_follow_card + 1
        val type_auto_play_follow_card = type_video_small_card + 1
        val type_picture_follow = type_auto_play_follow_card + 1
        val adapter = MultiBindingPagingDataAdapter(call)

        adapter.addBindingItem(VideoPagingDataAdapterType.type_text_card(layoutInflater))
        adapter.addBindingItem(VideoPagingDataAdapterType.type_follow_card(layoutInflater))
        adapter.addBindingItem(VideoPagingDataAdapterType.type_video_small_card(layoutInflater))
        adapter.addBindingItem(VideoPagingDataAdapterType.type_auto_play_follow_card(layoutInflater))

        adapter
    }

    override fun onFirstResume() {
        super.onFirstResume()

        try {
            lifecycleScope.launch {
                mViewModel.getVideoPage(url).collect {
                    mAdapter.submitData(it)
                }
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}