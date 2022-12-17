package com.pp.module_discovery.ui.topic

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_base.adapter.attachStateView
import com.pp.library_base.adapter.onErrorListener
import com.pp.library_base.base.ThemeActivity
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_common.adapter.VideoPagingDataAdapterType
import com.pp.library_common.model.ItemModel
import com.pp.library_common.model.MetroBannerItemViewModel
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.ApiRequest
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.ItemBannerBinding
import com.pp.library_ui.utils.StateView
import com.pp.module_discovery.databinding.ActivityTopicListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Route(path = RouterPath.Discovery.activity_topic_list)
class TopicListActivity : ThemeActivity<ActivityTopicListBinding, TopicListViewModel>() {

    @JvmField
    @Autowired(name = "apiRequest")
    var apiRequest: ApiRequest? = null

    override val mBinding by lazy { ActivityTopicListBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<TopicListViewModel> {
        return TopicListViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        initRecycler()
        apiRequest?.run {
            initData(this)
        }
        initToolbar()
        addObserver()
    }

    private val mAdapter by lazy {
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

                return result
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                val result =
                    if (oldItem is Metro && newItem is Metro) {
                        oldItem.metroData?.resourceId == newItem.metroData?.resourceId
                    } else if (oldItem is ItemModel<*> && newItem is ItemModel<*>) {
                        (oldItem.data as Card).cardUniqueId == (newItem.data as Card).cardUniqueId
                    } else {
                        oldItem == newItem
                    }
                return result
            }
        }
        val adapter = MultiBindingPagingDataAdapter<Any>(call)
        // item type (唯一)
        // feed_cover_large_video 类型
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_large_video(layoutInflater))

        // feed_cover_small_video 类型
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_small_video(layoutInflater))

        adapter.addBindingItem(MetroPagingDataAdapterType.description_text(layoutInflater))

        adapter
    }

    private fun initRecycler() {
        mBinding.recycler.layoutManager = LinearLayoutManager(this)

        mBinding.recycler.adapter = mAdapter.withLoadStateFooter(
            DefaultLoadMoreStateAdapter(lifecycle = lifecycle, mAdapter.onErrorListener())
        )
    }

    private fun initData(api: ApiRequest) {
        lifecycleScope.launch {
            mAdapter.attachStateView(
                StateView.DefaultBuilder(lifecycle, mBinding.topicRefresh)
                    .setOnErrorClickListener(mAdapter.onErrorListener())
                    .setThemeViewModel(mThemeViewModel)
                    .build()
            )
        }
        lifecycleScope.launch {
            mViewModel.getPagingData(api).collectLatest {
                mAdapter.submitData(lifecycle, it)
            }


        }
    }

    private fun initToolbar() {

    }

    private fun addObserver() {

    }

}