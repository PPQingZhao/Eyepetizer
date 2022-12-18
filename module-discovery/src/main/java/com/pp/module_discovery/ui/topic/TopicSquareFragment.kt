package com.pp.module_discovery.ui.topic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_base.adapter.attachStateView
import com.pp.library_base.adapter.onErrorListener
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_common.model.ItemModel
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.utils.StateView
import com.pp.module_discovery.databinding.FragmentTopicSquareBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopicSquareFragment : ThemeFragment<FragmentTopicSquareBinding, TopicSquareFragViewModel>() {

    override val mBinding by lazy { FragmentTopicSquareBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<TopicSquareFragViewModel> {
        return TopicSquareFragViewModel::class.java
    }

    private var pageLabel = ""
    private var pageType = ""

    companion object {
        fun newInstance(pageLabel: String, pageType: String): TopicSquareFragment{
            val args = Bundle()
            args.putString("pageLabel", pageLabel)
            args.putString("pageType", pageType)

            val fragment = TopicSquareFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            pageLabel = getString("pageLabel")?: ""
            pageType = getString("pageType")?: ""
        }
    }

    override fun onFirstResume() {
        super.onFirstResume()
        initRecycler()
        initData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_large_video(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_small_video(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.description_text(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_detail_topic(layoutInflater))

        adapter
    }

    private fun initRecycler() {
        mBinding.recycler.layoutManager = LinearLayoutManager(requireContext())

        mBinding.recycler.adapter = mAdapter.withLoadStateFooter(
            DefaultLoadMoreStateAdapter(lifecycle = lifecycle, mAdapter.onErrorListener())
        )
    }

    private var job: Job? = null
    private fun initData() {
        lifecycleScope.launch {
            mAdapter.attachStateView(
                StateView.DefaultBuilder(lifecycle, mBinding.topicRefresh)
                    .setOnErrorClickListener(mAdapter.onErrorListener())
                    .setThemeViewModel(requireTheme())
                    .build()
            )
        }
        job = lifecycleScope.launch {
            mViewModel.getPagingData(pageLabel, pageType).collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        job?.cancel()
    }

}