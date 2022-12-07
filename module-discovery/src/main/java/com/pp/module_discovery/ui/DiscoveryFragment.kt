package com.pp.module_discovery.ui

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.adapter.*
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.utils.StateView
import com.pp.module_discovery.databinding.FragmentDiscoveryBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Route(path = RouterPath.Discovery.fragment_discovery)
class DiscoveryFragment : ThemeFragment<FragmentDiscoveryBinding, DiscoveryViewModel>() {
    override val mBinding: FragmentDiscoveryBinding by lazy {
        FragmentDiscoveryBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<DiscoveryViewModel> {
        return DiscoveryViewModel::class.java
    }

    private val mAdapter: MultiBindingPagingDataAdapter<Any> by lazy {

        val itemCallBack = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

        }

        val adapter = MultiBindingPagingDataAdapter<Any>(itemCallBack)

        adapter.addBindingItem(MetroPagingDataAdapterType.icon_grid(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.set_slide_metro_list(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.stacked_slide_cover_image(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.default_web(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.head_item(layoutInflater))

        adapter
    }

    override fun onFirstResume() {
        mAdapter.attachRecyclerView(viewLifecycleOwner.lifecycle, mBinding.recycler)
        lifecycleScope.launch {
            mAdapter.attachRefreshView(mBinding.discoveryRefresh)
        }

        lifecycleScope.launch {
            mAdapter.attachStateView(
                StateView.DefaultBuilder(viewLifecycleOwner.lifecycle, mBinding.discoveryRefresh)
                    .setOnErrorClickListener(mAdapter.onErrorListener())
                    .setThemeViewModel(requireTheme())
                    .build()
            )
        }
        lifecycleScope.launch {
            mViewModel.getPagingData().collectLatest {
                mAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

}