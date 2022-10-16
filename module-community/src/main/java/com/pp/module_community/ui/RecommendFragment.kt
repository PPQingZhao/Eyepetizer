package com.pp.module_community.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_router_service.services.RouterPath
import com.pp.module_community.adapter.RecPagingDataAdapter
import com.pp.module_community.databinding.FragmentCommunityRecBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = RouterPath.Community.fragment_community)
class RecommendFragment : LifecycleFragment<FragmentCommunityRecBinding, RecommendViewModel>() {

    override val mBinding: FragmentCommunityRecBinding by lazy {
        FragmentCommunityRecBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<RecommendViewModel> {
        return RecommendViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private val mAdapter: RecPagingDataAdapter by lazy { RecPagingDataAdapter() }
    private fun initRecyclerView() {
        mBinding.rv.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rv.adapter = mAdapter
    }

    override fun onFirstResume() {
        super.onFirstResume()
        lifecycleScope.launch {
            mViewModel.getData().collect {
                mAdapter.submitData(it)
            }
        }
    }
}