package com.pp.module_home.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.module_home.adapter.FollowPagingDataAdapter
import com.pp.module_home.databinding.FragmentFollowBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowFragment : LifecycleFragment<FragmentFollowBinding, FollowViewModel>() {
    override val mBinding: FragmentFollowBinding by lazy {
        FragmentFollowBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<FollowViewModel> {
        return FollowViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    private val followAdapter: FollowPagingDataAdapter by lazy { FollowPagingDataAdapter() }
    private fun initRecyclerView() {
        mBinding.followRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.followRecyclerview.adapter = followAdapter
    }

    override fun onFirstResume() {
        lifecycleScope.launch {
            mViewModel.getData().collect {
                followAdapter.submitData(it)
            }
        }
    }

}

