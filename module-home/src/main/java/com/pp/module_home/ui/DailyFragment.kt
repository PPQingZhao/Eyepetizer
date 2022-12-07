package com.pp.module_home.ui

import androidx.lifecycle.lifecycleScope
import com.pp.library_base.adapter.attachRecyclerView
import com.pp.library_base.adapter.attachRefreshView
import com.pp.library_base.adapter.attachStateView
import com.pp.library_base.adapter.onErrorListener
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_ui.utils.StateView
import com.pp.module_home.databinding.FragmentDailyBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class DailyFragment : ThemeFragment<FragmentDailyBinding, DailyViewModel>() {
    override val mBinding: FragmentDailyBinding by lazy {
        FragmentDailyBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<DailyViewModel> {
        return DailyViewModel::class.java
    }

    private val dailyAdapter by lazy { MetroPagingDataAdapterType.largeVideoCardPagingDataAdapter() }

    override fun onFirstResume() {

        dailyAdapter.attachRecyclerView(viewLifecycleOwner.lifecycle,mBinding.dailyRecyclerview)
        lifecycleScope.launch {
            dailyAdapter.attachRefreshView(mBinding.dailyRefresh)
        }

        lifecycleScope.launch {
            dailyAdapter.attachStateView(
                StateView.DefaultBuilder(lifecycle, mBinding.dailyRecyclerview)
                    .setOnErrorClickListener(dailyAdapter.onErrorListener())
                    .setThemeViewModel(requireTheme())
                    .build()
            )
        }
        lifecycleScope.launch {
            mViewModel.getData().collect {
                dailyAdapter.submitData(lifecycle, it)
            }
        }
    }

}

