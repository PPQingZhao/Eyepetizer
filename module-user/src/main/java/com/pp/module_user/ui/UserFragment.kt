package com.pp.module_user.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.adapter.MultiBindingPagingDataAdapter
import com.pp.library_base.adapter.attachRecyclerView
import com.pp.library_base.adapter.attachStateView
import com.pp.library_base.adapter.onErrorListener
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.utils.StateView
import com.pp.module_user.databinding.FragmentUserBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.abs

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
    }

    private fun initTitle() {
        // 自定义分发 windowInsets
        // 拦截 appBarLayout WindowInsets, 分发给toolbar，实现沉浸式状态栏
        mBinding.userAbl.setOnApplyWindowInsetsListener { v, insets ->
            mBinding.userToolbar.dispatchApplyWindowInsets(insets)
        }
        mBinding.userAbl.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val offset = abs(verticalOffset)

            // toolbar 透明度跟随滚动变化
            mBinding.userToolbar.alpha = offset * 1.0F / appBarLayout.totalScrollRange

            /* // 展开
             if (offset == 0) {
                 mBinding.userTvTitle.visibility = View.GONE
                 // 折叠
             } else if (offset == appBarLayout.totalScrollRange) {
                 mBinding.userTvTitle.visibility = View.VISIBLE
             }*/

        }
    }

    private val mAdapter by lazy {
        val adapter = MultiBindingPagingDataAdapter(MetroPagingDataAdapterType.DIFF_CALLBACK)

        adapter.addBindingItem(MetroPagingDataAdapterType.description_text(layoutInflater))
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_item_detail_image(layoutInflater, true))
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_item_detail_video(layoutInflater, true))
        adapter.addBindingItem(MetroPagingDataAdapterType.feed_cover_large_image(layoutInflater))

        adapter
    }

    override fun onFirstResume() {
        mAdapter.attachRecyclerView(viewLifecycleOwner.lifecycle, mBinding.userRecyclerview)


        lifecycleScope.launch {
            mAdapter.attachStateView(
                StateView.DefaultBuilder(lifecycle, mBinding.userRecyclerview)
                    .setOnErrorClickListener(mAdapter.onErrorListener())
                    .setThemeViewModel(requireTheme())
                    .build()
            )
        }
        mViewModel.userInfo.observe(this) {
            if (null == it) {
                return@observe
            }

            lifecycleScope.launch {
                mViewModel.getNvaTabData(
                    it.uid,
                    it.navTabs.navList[0].pageType,
                    it.navTabs.navList[0].pageLabel,
                ).collect {
                    mAdapter.submitData(it)
                }
            }
        }
    }
}