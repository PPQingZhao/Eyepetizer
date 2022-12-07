package com.pp.module_home.ui

import androidx.lifecycle.lifecycleScope
import com.pp.library_base.adapter.*
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_ui.utils.StateView
import com.pp.module_home.databinding.FragmentFollowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowFragment : ThemeFragment<FragmentFollowBinding, FollowViewModel>() {
    override val mBinding: FragmentFollowBinding by lazy {
        FragmentFollowBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<FollowViewModel> {
        return FollowViewModel::class.java
    }

    private val followAdapter by lazy {
        val adapter = MultiBindingPagingDataAdapter(MetroPagingDataAdapterType.DIFF_CALLBACK)
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_item_detail(layoutInflater))

        adapter
    }


    override fun onFirstResume() {

        followAdapter.attachRecyclerView(viewLifecycleOwner.lifecycle, mBinding.followRecyclerview)
        lifecycleScope.launch {
            followAdapter.attachRefreshView(mBinding.followRefresh)
        }

        lifecycleScope.launch {
            followAdapter.attachStateView(
                StateView.DefaultBuilder(lifecycle, mBinding.followRecyclerview)
                    .setOnErrorClickListener(followAdapter.onErrorListener())
                    .setThemeViewModel(requireTheme())
                    .build()
            )
        }

        lifecycleScope.launch(Dispatchers.IO) {
            mViewModel.getData().collect {
                followAdapter.submitData(lifecycle, it)
            }
        }
    }


}

