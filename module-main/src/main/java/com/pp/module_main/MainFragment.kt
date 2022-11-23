package com.pp.module_main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.library_common.routerservice.RouterServices
import com.pp.library_router_service.services.RouterPath
import com.pp.module_main.databinding.FragmentMainBinding

class MainFragment : TabPagerFragment<FragmentMainBinding, MainViewModel>() {
    override val mBinding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }

    override fun getTabLayout(): TabLayout {
        return mBinding.mainTabLayout
    }

    override fun getViewPager(): ViewPager2 {
        // todo: 待解决bug:  isUserInputEnabled = false 会导致懒加载失效(界面可见时,没有执行生命周期 onResume)
        mBinding.mainViewpager.isUserInputEnabled = false
        mBinding.mainViewpager.offscreenPageLimit = 3
        return mBinding.mainViewpager
    }

    override fun getModelClazz(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    @SuppressLint("ClickableViewAccessibility")
    fun getPager(): TabPager {

        // tab 资源
        val tabImageSwitchers = createTabImageSwitcher()
        val factory = object : Pager.FragmentFactory {

            var selectedTabImageSwitcher: TabImageSwitcher? = null
            override fun create(position: Int): Fragment {

                val tabImageSwitcher = tabImageSwitchers[position]
                if (tabImageSwitcher != selectedTabImageSwitcher) {
                    selectedTabImageSwitcher?.isSelected = false
                    selectedTabImageSwitcher = tabImageSwitcher
                    selectedTabImageSwitcher?.isSelected = true
                }

                return when (position) {
                    0 -> ARouter.getInstance().build(RouterPath.Home.fragment_home)
                        .navigation() as Fragment
                    1 -> ARouter.getInstance().build(RouterPath.Community.fragment_community)
                        .navigation() as Fragment
                    2 -> ARouter.getInstance().build(RouterPath.Discovery.fragment_discovery)
                        .navigation() as Fragment
                    3 -> ARouter.getInstance().build(RouterPath.User.fragment_user)
                        .navigation() as Fragment
                    else -> TestFragment()
                }
            }
        }

        // 创建 pager
        val tabPager = TabPager(tabImageSwitchers.size, factory)
        // 创建 tab
        tabPager.initTabs { position ->
            val tabImageSwitcher = tabImageSwitchers[position]
            tabImageSwitcher.setOnTouchListener { v, event ->

                if (event.action != MotionEvent.ACTION_DOWN) {
                    return@setOnTouchListener false
                }
                if (tabImageSwitcher.selectedIcon != com.pp.library_ui.R.mipmap.icon_tab_mine_select) {
                    return@setOnTouchListener false
                }

                // 存在用户
                if (RouterServices.userService.hasUser()) {
                    return@setOnTouchListener false
                }

                //不存在用户,跳转登录
                val postcard = ARouter.getInstance().build(RouterPath.User.activity_login)
                LogisticsCenter.completion(postcard)
                // 执行跳转登录
                loginLauncher.launch(
                    Intent(
                        context,
                        postcard.destination
                    )
                )
                true
            }

            TabPager.Tab(tabImageSwitcher)
        }
        return tabPager
    }

    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != Activity.RESULT_OK) {
                return@registerForActivityResult
            }
            mBinding.mainViewpager.currentItem = 3
        }


    override fun onFirstResume() {
        mHelper.attach(getPager(), false)
    }

    private fun createTabImageSwitcher(): List<TabImageSwitcher> {
        val imageSwitchers = mutableListOf<TabImageSwitcher>()

        imageSwitchers.add(
            TabImageSwitcher(
                requireContext(),
                com.pp.library_ui.R.mipmap.icon_tab_home_unselect,
                com.pp.library_ui.R.mipmap.icon_tab_home_select
            )
        )

        imageSwitchers.add(
            TabImageSwitcher(
                requireContext(),
                com.pp.library_ui.R.mipmap.icon_tab_social_unselect,
                com.pp.library_ui.R.mipmap.icon_tab_social_select
            )
        )
        imageSwitchers.add(
            TabImageSwitcher(
                requireContext(),
                com.pp.library_ui.R.mipmap.icon_tab_discover_unselect,
                com.pp.library_ui.R.mipmap.icon_tab_discover_select
            )
        )
        imageSwitchers.add(
            TabImageSwitcher(
                requireContext(),
                com.pp.library_ui.R.mipmap.icon_tab_mine_unselect,
                com.pp.library_ui.R.mipmap.icon_tab_mine_select
            )
        )

        return imageSwitchers
    }

}

@SuppressLint("ViewConstructor")
class TabImageSwitcher(
    context: Context,
    @DrawableRes val unSelectedIcon: Int,
    @DrawableRes val selectedIcon: Int
) : ImageSwitcher(context) {
    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setFactory {
            val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            layoutParams.gravity = Gravity.CENTER
            val imageView = ImageView(context)
            imageView.layoutParams = layoutParams
            imageView.setPadding(10, 10, 10, 10)
            imageView
        }
    }

    @SuppressLint("ResourceType")
    override fun setSelected(selected: Boolean) {
        if (isSelected == selected) {
            // do not thing
            return
        }
        if (selected) {
            setInAnimation(context, com.pp.library_ui.R.anim.anim_tab_selected_in)
            setOutAnimation(context, com.pp.library_ui.R.anim.anim_tab_unselected_out)
            setImageResource(selectedIcon)
        } else {
            setInAnimation(context, com.pp.library_ui.R.anim.anim_tab_unselected_in)
            setOutAnimation(context, com.pp.library_ui.R.anim.anim_tab_selected_out)
            setImageResource(unSelectedIcon)
        }
        super.setSelected(selected)
    }
}