package com.pp.module_discovery.ui.tag

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.VideoPagingDataAdapterType
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.module_discovery.databinding.FragmentTagSquareBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TagSquareFragment(tab: TagDetailBean.TabInfo.Tab?) : ThemeFragment<FragmentTagSquareBinding, TagSquareViewModel>() {
    override val mBinding by lazy {
        FragmentTagSquareBinding.inflate(layoutInflater)
    }
    override fun getModelClazz(): Class<TagSquareViewModel> {
        return TagSquareViewModel::class.java
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

        val adapter = MultiBindingPagingDataAdapter(call)

        adapter.addBindingItem(VideoPagingDataAdapterType.type_text_card(layoutInflater))
        adapter.addBindingItem(VideoPagingDataAdapterType.type_follow_card(layoutInflater))
        adapter.addBindingItem(VideoPagingDataAdapterType.type_video_small_card(layoutInflater))
        adapter.addBindingItem(VideoPagingDataAdapterType.type_auto_play_follow_card(layoutInflater))
        adapter.addBindingItem(VideoPagingDataAdapterType.type_picture_follow_card(layoutInflater))

        adapter
    }

    override fun onFirstResume() {
        super.onFirstResume()

        try {
            lifecycleScope.launch {
                mViewModel.getDynamicsPagingData(url).collect {
                    mAdapter.submitData(it)
                }
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}