package com.pp.module_user.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.databinding.FragmentUserBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = RouterPath.User.fragment_user)
class UserFragment : ThemeFragment<FragmentUserBinding, UserViewModel>() {
    override val mBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<UserViewModel> {
        return UserViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTitle()
        initRecyclerView()

        mViewModel.userInfo.observe(this) {
            lifecycleScope.launch {
                mViewModel.getNvaTabData(
                    it?.uid ?: 0,
                    it?.navTabs?.navList?.get(0)?.pageType ?: "",
                    it?.navTabs?.navList?.get(0)?.pageLabel ?: "",
                ).collect {
                    mAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                }
            }
        }

    }

    private fun initTitle() {
        mBinding.userAbl.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val offset = Math.abs(verticalOffset)

            // 展开
            if (offset == 0) {
                mBinding.userTvTitle.visibility = View.GONE
                // 折叠
            } else if (offset == appBarLayout.totalScrollRange) {
                mBinding.userTvTitle.visibility = View.VISIBLE
            }
        }
    }

    private val mAdapter by lazy {
        val adapter = MultiBindingPagingDataAdapter(MetroPagingDataAdapterType.DIFF_CALLBACK)

        adapter.addBindingItem(MetroPagingDataAdapterType.description_text(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_item_detail(layoutInflater, true))

        adapter
    }

    private fun initRecyclerView() {
        mBinding.userRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.userRecyclerview.adapter =
            mAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter(lifecycle = lifecycle) {
                mAdapter.retry()
            })

    }
}