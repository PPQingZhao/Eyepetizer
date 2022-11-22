package com.pp.module_home.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.module_home.databinding.FragmentDailyBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class DailyFragment : LifecycleFragment<FragmentDailyBinding, DailyViewModel>() {
    override val mBinding: FragmentDailyBinding by lazy {
        FragmentDailyBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<DailyViewModel> {
        return DailyViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initRefreshView()
    }

    private fun initRefreshView() {

        mBinding.dailyRefresh.setOnRefreshListener{
            dailyAdapter.refresh()
        }
        lifecycleScope.launch {
            dailyAdapter.loadStateFlow.collectLatest {
                mBinding.dailyRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
    }

    private val dailyAdapter by lazy { MetroPagingDataAdapterType.largeVideoCardPagingDataAdapter }
    private fun initRecyclerView() {
        mBinding.dailyRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.dailyRecyclerview.adapter =
            dailyAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter {
                dailyAdapter.retry()
            })

    }

    override fun onFirstResume() {
        lifecycleScope.launch {
            mViewModel.getData().collect {
                dailyAdapter.submitData(lifecycle, it)
            }
        }
    }

}

