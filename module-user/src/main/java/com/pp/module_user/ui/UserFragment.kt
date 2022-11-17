package com.pp.module_user.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_common.model.MetroFollowItemViewModel2
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.ItemFollowCardBindingImpl
import com.pp.library_ui.databinding.ItemToBeDevelopedBindingImpl
import com.pp.module_user.databinding.FragmentUserBinding
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.launch

@Route(path = RouterPath.User.fragment_user)
class UserFragment : LifecycleFragment<FragmentUserBinding, UserViewModel>() {
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
                    it.uid,
                    it.navTabs.navList[0].pageType,
                    it.navTabs.navList[0].pageLabel
                ).observe(this@UserFragment) {
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
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_item_detail(layoutInflater))

        adapter
    }

    private fun initRecyclerView() {
        mBinding.userRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.userRecyclerview.adapter =
            mAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter {
                mAdapter.retry()
            })

    }
}