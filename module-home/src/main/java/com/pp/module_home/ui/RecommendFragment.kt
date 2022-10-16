package com.pp.module_home.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.DefaultLoadStateAdapter
import com.pp.module_home.adapter.RecommendPagingDataAdapter
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

    private val recommendAdapter by lazy { RecommendPagingDataAdapter() }

    private fun initRecyclerView() {
        mBinding.recommendRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.recommendRecyclerview.adapter = recommendAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
    }

    override fun onFirstResume() {

        lifecycleScope.launch {
            mViewModel.getData().collect {
                recommendAdapter.submitData(it)
            }
        }
    }

}

