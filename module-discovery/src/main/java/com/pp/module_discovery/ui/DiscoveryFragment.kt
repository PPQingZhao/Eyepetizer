package com.pp.module_discovery.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.module_discovery.databinding.FragmentDiscoveryBinding
import com.pp.mvvm.LifecycleFragment

@Route(path = RouterPath.Discovery.fragment_discovery)
class DiscoveryFragment : LifecycleFragment<FragmentDiscoveryBinding, DiscoveryViewModel>() {
    override val mBinding: FragmentDiscoveryBinding by lazy {
        FragmentDiscoveryBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<DiscoveryViewModel> {
        return DiscoveryViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        addObserver()
    }

    private val mAdapter: MultiBindingAdapter<Any> by lazy {
        val adapter = MultiBindingAdapter<Any>()

        adapter.addBindingItem(MetroPagingDataAdapterType.icon_grid(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.set_slide_metro_list(layoutInflater))

        adapter
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())

        mBinding.recycler.layoutManager = layoutManager
        mBinding.recycler.adapter = mAdapter
    }

    private fun addObserver() {
        mViewModel.dataList.observe(viewLifecycleOwner) {
            mAdapter.addDatas(it)
        }
    }

    override fun onFirstResume() {
        super.onFirstResume()

        mViewModel.getData()
    }
}