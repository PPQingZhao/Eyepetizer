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
import com.pp.library_common.adapter.VideoPagingDataAdapterType
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.utils.StateView
import com.pp.module_discovery.databinding.ActivityTopicBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Route(path = RouterPath.Discovery.activity_topic)
class TopicActivity : ThemeActivity<ActivityTopicBinding, TopicViewModel>() {

    @JvmField
    @Autowired(name = "url")
    var url: String = ""

    override val mBinding by lazy { ActivityTopicBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<TopicViewModel> {
        return TopicViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        val realUrl = EyepetizerService.URL_TOPIC_INTERNAL + url

        initToolbar()
        initRecycler()
        initData(realUrl)
        addObserver()
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
        adapter.addBindingItem(VideoPagingDataAdapterType.type_picture_follow_card(layoutInflater))

        adapter
    }

    private fun initRecycler() {
        mBinding.recycler.layoutManager = LinearLayoutManager(this)

        mBinding.recycler.adapter = mAdapter.withLoadStateFooter(
            DefaultLoadMoreStateAdapter(lifecycle = lifecycle, mAdapter.onErrorListener())
        )
    }

    private fun initData(realUrl: String) {
        lifecycleScope.launch {
            mAdapter.attachStateView(
                StateView.DefaultBuilder(lifecycle, mBinding.topicRefresh)
                    .setOnErrorClickListener(mAdapter.onErrorListener())
                    .setThemeViewModel(mThemeViewModel)
                    .build()
            )
        }
        lifecycleScope.launch {
            mViewModel.getPagingData(realUrl).collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun initToolbar() {

    }

    private fun addObserver() {

    }

}