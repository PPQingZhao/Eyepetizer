package com.pp.module_home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_common.model.ItemModel
import com.pp.library_common.model.MetroBannerItemViewModel
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.model.MetroSmallVideoCardItemViewModel
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean.Card.CardData.Body.Metro
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.ItemBannerBinding
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.library_ui.databinding.ItemVideoCardBinding
import com.pp.library_ui.databinding.ItemVideoSmallCardBinding
import com.pp.module_home.databinding.FragmentRecommendBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
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

    }

    private val multiAdapter by lazy {
        val call = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
        }
        val adapter = MultiBindingPagingDataAdapter<Any>(call)
        // item type (唯一)
        val type_large_video = 1
        val type_small_video = type_large_video + 1
        val type_small_slide_image = type_small_video + 1
        // feed_cover_large_video 类型
        adapter.addBindingItem(
            DefaultViewBindingItem<Metro>(
                type_large_video,
                { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_large_video },
                { ItemVideoCardBinding.inflate(layoutInflater, it, false) },
                { binding, item, cacheItemViewModel ->
                    if (cacheItemViewModel is MetroLargeVideoCardItemViewModel) cacheItemViewModel
                    else MetroLargeVideoCardItemViewModel(item)
                })
        )

        // feed_cover_small_video 类型
        adapter.addBindingItem(
            DefaultViewBindingItem<Metro>(
                type_small_video,
                { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_small_video },
                { ItemVideoSmallCardBinding.inflate(layoutInflater, it, false) },
                { binding, item, cacheItemViewModel ->
                    if (cacheItemViewModel is MetroSmallVideoCardItemViewModel) cacheItemViewModel
                    else MetroSmallVideoCardItemViewModel(item)
                })
        )

        // slide_cover_image_with_footer 轮播图类型 (数据源:set_banner_list)
        adapter.addBindingItem(
            DefaultViewBindingItem<ItemModel<List<Metro>>>(
                type_small_slide_image,
                { it?.type == EyepetizerService2.CardType.SET_BANNER_LIST },
                // TODO: 待实现 -> MetroSlideImageWithFooterViewModel
                { ItemBannerBinding.inflate(layoutInflater, it, false) },
                { binding, item, cacheItemViewModel ->
                    if (cacheItemViewModel is MetroBannerItemViewModel) cacheItemViewModel
                    else MetroBannerItemViewModel(metroList = item?.data)
                })
        )
        adapter
    }

    private fun initRecyclerView() {

        mBinding.recommendRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.recommendRecyclerview.adapter =
            multiAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter {
                multiAdapter.retry()
            })
    }

    override fun onFirstResume() {

        lifecycleScope.launch {
            /* EyepetizerService2.api.getPageData(EyepetizerService2.URL_TOPIC_HOT).let {

                 Log.e("TAG","${it.result.cardList.size}")
             }*/
            mViewModel.getPageData().collect {
                multiAdapter.submitData(it)
            }
        }
    }

}

