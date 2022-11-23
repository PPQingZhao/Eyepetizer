package com.pp.module_community.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_router_service.services.RouterPath
import com.pp.module_community.GridDivider
import com.pp.module_community.adapter.SquareAdapter
import com.pp.module_community.databinding.FragmentSquareBinding
import com.pp.module_community.respository.SquareType
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Route(path = RouterPath.Community.fragment_community)
class SquareFragment : LifecycleFragment<FragmentSquareBinding, SquareViewModel>() {

    override val mBinding: FragmentSquareBinding by lazy {
        FragmentSquareBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<SquareViewModel> {
        return SquareViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initRefreshView()
        initAppbar()
    }

    private fun initAppbar() {
        mBinding.includeTitle.ivStart.setOnClickListener {
            navigateSearch()
        }

        mBinding.includeTitle.ivEnd.setOnClickListener {

        }
    }

    private fun navigateSearch() {
        ARouter.getInstance().build(RouterPath.Search.activity_search).navigation()
    }

    private fun initRefreshView() {
        mBinding.communityRefresh.setOnRefreshListener {
            mAdapter.refresh()
        }
        lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest {
                mBinding.communityRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
    }

    private val mAdapter: SquareAdapter by lazy { SquareAdapter() }
    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position >= mAdapter.itemCount) {
                    return 1
                }
                val viewType = mAdapter.getItemViewType(position)
                if (viewType == SquareType.TYPE_VIDEO_SMALL) {
                    return 1
                }
                return 2
            }
        }
        mBinding.rv.layoutManager = layoutManager

        // 只绘制 TYPE_VIDEO_SMALL
        mBinding.rv.addItemDecoration(GridDivider {
            val itemData = mAdapter.getItemData(position = it)
            itemData?.itemType == SquareType.TYPE_VIDEO_SMALL
        })

        mBinding.rv.adapter =
            mAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter(lifecycle = lifecycle) {
                mAdapter.retry()
            })
    }

    override fun onFirstResume() {
        super.onFirstResume()
        lifecycleScope.launch {
            try {
                mViewModel.getData().collect {
                    mAdapter.submitData(it)
                }
            } catch (e: Exception) {
                Log.e("TAG", "getData err: ${e.message}")
            }
        }

    }

}