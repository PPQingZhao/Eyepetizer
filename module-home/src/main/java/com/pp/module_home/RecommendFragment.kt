package com.pp.module_home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.module_home.adapter.RecommendAdapter
import com.pp.module_home.databinding.FragmentRecommendBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RecommendFragment : LifecycleFragment<FragmentRecommendBinding, RecommendViewModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_recommend;
    }

    override fun getModelClazz(): Class<RecommendViewModel> {
        return RecommendViewModel::class.java
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    private val recommendAdapter by lazy { RecommendAdapter() }

    private fun initRecyclerView() {
        mBinding.recommendRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.recommendRecyclerview.adapter = recommendAdapter
    }

    override fun onFirstResume() {

        lifecycleScope.launch {
            lifecycle
            val data = mViewModel.getData().collect {
                recommendAdapter.submitData(it)
            }
        }
    }
}

