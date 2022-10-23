package com.pp.module_community.ui

import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_network.eyepetizer.ApiService
import com.pp.library_router_service.services.RouterPath
import com.pp.module_community.adapter.RecPagingDataAdapter
import com.pp.module_community.api.CommunityApi
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
        mBinding.rv.adapter = mAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter{
            mAdapter.retry()
        })
    }

    override fun onFirstResume() {
        super.onFirstResume()
        lifecycleScope.launch {
            /*mViewModel.getData().collect {
                mAdapter.submitData(it)
            }*/
        }

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            try {
                val data = ApiService.api.getPageData()
            } catch (e: Exception) {
                Log.e("TAG", "err: ${e.message}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getMicInfo() {
        context?.apply {
            val audioService = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val size = audioService.microphones.size
            Log.e("TAG", "mic size: $size")
        }
    }
}