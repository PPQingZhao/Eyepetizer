package com.pp.module_discovery.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.module_discovery.databinding.FragmentDiscoveryBinding

@Route(path = RouterPath.Discovery.fragment_discovery)
class DiscoveryFragment : ThemeFragment<FragmentDiscoveryBinding, DiscoveryViewModel>() {
    override val mBinding: FragmentDiscoveryBinding by lazy {
        FragmentDiscoveryBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<DiscoveryViewModel> {
        return DiscoveryViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initRefreshView()
        addObserver()
    }

    private fun initRefreshView() {
        mBinding.discoveryRefresh.setOnRefreshListener {
            mViewModel.getData()
        }
    }

    private val mAdapter: MultiBindingAdapter<Any> by lazy {
        val adapter = MultiBindingAdapter<Any>()

        adapter.addBindingItem(MetroPagingDataAdapterType.icon_grid(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.set_slide_metro_list(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.stacked_slide_cover_image(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.default_web(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.head_item(layoutInflater))

        adapter
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())

        mBinding.recycler.layoutManager = layoutManager
        mBinding.recycler.adapter = mAdapter
    }

    private fun addObserver() {
        mViewModel.dataList.observe(viewLifecycleOwner) {
            mBinding.discoveryRefresh.isRefreshing = false
            mAdapter.setDataList(it)
        }
    }

    override fun onFirstResume() {
        super.onFirstResume()

        mViewModel.getData()
    }
}