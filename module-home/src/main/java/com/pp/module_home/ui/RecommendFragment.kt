package com.pp.module_home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_common.model.ItemModel
import com.pp.library_common.model.MetroBannerItemViewModel
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.ItemBannerBinding
import com.pp.module_home.databinding.FragmentRecommendBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecommendFragment : LifecycleFragment<FragmentRecommendBinding, RecommendViewModel>() {
    override val mBinding: FragmentRecommendBinding by lazy {
        FragmentRecommendBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<RecommendViewModel> {
        return RecommendViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initRefreshView()
    }

    private fun initRefreshView() {
        mBinding.recommendRefresh.setOnRefreshListener {
            multiAdapter.refresh()
        }

        lifecycleScope.launch {
            multiAdapter.loadStateFlow.collectLatest {
                mBinding.recommendRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
    }

    private val multiAdapter by lazy {
        val call = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                val result =
                    if (oldItem is Metro && newItem is Metro) {
                        oldItem.metroId == newItem.metroId
                    } else if (oldItem is ItemModel<*> && newItem is ItemModel<*>) {
                        (oldItem.data as Card).cardUniqueId == (newItem.data as Card).cardUniqueId
                    } else {
                        oldItem == newItem
                    }

//                Log.e("TAG", "111  result: ${result}")
                return result
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                val result =
                    if (oldItem is Metro && newItem is Metro) {
                        oldItem.metroData.resourceId == newItem.metroData.resourceId
                    } else if (oldItem is ItemModel<*> && newItem is ItemModel<*>) {
                        (oldItem.data as Card).cardUniqueId == (newItem.data as Card).cardUniqueId
                    } else {
                        oldItem == newItem
                    }
//                Log.e("TAG", "2222  result: ${result}")
                return result
            }
        }
        val adapter = MultiBindingPagingDataAdapter<Any>(call)
        // item type (唯一)
        // feed_cover_large_video 类型
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_large_video(layoutInflater))

        // feed_cover_small_video 类型
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_small_video(layoutInflater))

        val type_small_slide_image = MetroPagingDataAdapterType.type_end_value + 1
        // slide_cover_image_with_footer 轮播图类型 (数据源:set_banner_list)
        adapter.addBindingItem(
            DefaultViewBindingItem<ItemModel<Card>>(
                type_small_slide_image,
                { it?.type == EyepetizerService2.CardType.SET_BANNER_LIST },
                { ItemBannerBinding.inflate(layoutInflater, it, false) },
                { binding, item, cacheItemViewModel ->
                    if (cacheItemViewModel is MetroBannerItemViewModel) {
                        cacheItemViewModel.metroList = item?.data?.cardData?.body?.metroList
                        cacheItemViewModel
                    } else MetroBannerItemViewModel(metroList = item?.data?.cardData?.body?.metroList)
                })
        )
        adapter
    }

    private fun initRecyclerView() {

        mBinding.recommendRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.recommendRecyclerview.adapter =
            multiAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter(lifecycle = lifecycle) {
                multiAdapter.retry()
            })
    }

    override fun onFirstResume() {

        lifecycleScope.launch {
            mViewModel.getPageData().collect {
                multiAdapter.submitData(lifecycle, it)
            }
        }
    }

}

