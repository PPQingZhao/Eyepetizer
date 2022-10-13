package com.pp.module_home.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.module_home.adapter.DailyAdapter
import com.pp.module_home.databinding.FragmentDailyBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
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
    }

    private val dailyAdapter: DailyAdapter by lazy { DailyAdapter() }
    private fun initRecyclerView() {
        mBinding.dailyRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.dailyRecyclerview.adapter = dailyAdapter

    }

    override fun onFirstResume() {
        lifecycleScope.launch {
            mViewModel.getData().collect {
                dailyAdapter.submitData(it)
            }
        }
    }

}

