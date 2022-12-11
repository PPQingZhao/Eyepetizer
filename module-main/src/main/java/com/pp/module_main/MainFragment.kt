package com.pp.module_main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.view.MotionEvent
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.library_base.base.ThemeActivity
import com.pp.library_common.routerservice.RouterServices
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.R
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

    // tab 资源
    val tabImageSwitchers by lazy { createTabImageSwitcher() }

    @SuppressLint("ClickableViewAccessibility")
    fun getPager(): TabPager {

        val factory = object : Pager.FragmentFactory {

            var selectedTabImageSwitcher: TabImageSwitcher? = null
            override fun create(position: Int): Fragment {
                val tabImageSwitcher = tabImageSwitchers[position]
                if (tabImageSwitcher != selectedTabImageSwitcher) {
                    selectedTabImageSwitcher?.isSelected = true
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

        var curThemeId = 0
        // 创建 pager
        val tabPager = TabPager(tabImageSwitchers.size, factory)
        // 创建 tab
        tabPager.initTabs { position ->
            val tabImageSwitcher = tabImageSwitchers[position]
            tabImageSwitcher.setOnTouchListener { v, event ->

                if (position == mBinding.mainViewpager.currentItem) {
                    curThemeId =
                        if (curThemeId == R.style.Theme_Night) R.style.AppTheme else R.style.Theme_Night

                    (requireActivity() as ThemeActivity<*, *>).requireLightStatsBar(curThemeId == R.style.AppTheme)
                    requireActivity().setTheme(curThemeId)
                }

                if (event.action != MotionEvent.ACTION_DOWN) {
                    return@setOnTouchListener false
                }
                if (tabImageSwitcher.selectedIcon != R.drawable.ic_tab_selected_mine_black) {
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
                R.drawable.ic_tab_unselected_home_black,
                R.drawable.ic_tab_selected_home_black
            )
        )

        imageSwitchers.add(
            TabImageSwitcher(
                requireContext(),
                R.drawable.ic_tab_unselected_social_black,
                R.drawable.ic_tab_selected_social_black
            )
        )
        imageSwitchers.add(
            TabImageSwitcher(
                requireContext(),
                R.drawable.ic_tab_unselected_discovery_black,
                R.drawable.ic_tab_selected_discovery_black
            )
        )
        imageSwitchers.add(
            TabImageSwitcher(
                requireContext(),
                R.drawable.ic_tab_unselected_mine_black,
                R.drawable.ic_tab_selected_mine_black
            )
        )

        requireTheme().windowBackground.observe(this) { color ->
            imageSwitchers.forEach {
                it.setImageBackground(color)
            }
        }

        requireTheme().themeTint.observe(this) { tint ->
            imageSwitchers.forEach {
                it.setImageTintList(tint)
            }
        }

        return imageSwitchers
    }

}

@SuppressLint("ViewConstructor")
class TabImageSwitcher(
    context: Context,
    @DrawableRes val unSelectedIcon: Int,
    @DrawableRes val selectedIcon: Int
) : ImageSwitcher(context) {

    var imageTintList = ColorStateList.valueOf(Color.TRANSPARENT)
    var curImageBackgroundColor = Color.TRANSPARENT

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setFactory {
            val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            layoutParams.gravity = Gravity.CENTER
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                this.layoutParams = layoutParams
                imageTintList = imageTintList
                scaleX = 0.8f
                scaleY = 0.8f
                setBackgroundColor(curImageBackgroundColor)
            }
        }
        setImageResource(selectedIcon)
        setImageResource(unSelectedIcon)
    }

    fun setImageBackground(@ColorInt color: Int) {
        curImageBackgroundColor = color

        for (i in 0 until childCount) {
            getChildAt(i).setBackgroundColor(color)
        }
    }

    fun setImageTintList(@ColorInt tint: Int) {
        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_selected)
        states[1] = intArrayOf()

        val colors = intArrayOf(tint, tint)
        imageTintList = ColorStateList(states, colors)

        for (i in 0 until childCount) {
            (getChildAt(i) as ImageView).imageTintList = imageTintList
        }
    }

    @SuppressLint("ResourceType")
    override fun setSelected(selected: Boolean) {
        if (isSelected == selected) {
            // do not thing
            return
        }
        if (selected) {
            setInAnimation(context, R.anim.anim_tab_selected_in)
            setOutAnimation(context, R.anim.anim_tab_unselected_out)
//            setImageResource(selectedIcon)
            showNext()
        } else {
            setInAnimation(context, R.anim.anim_tab_unselected_in)
            setOutAnimation(context, R.anim.anim_tab_selected_out)
//            setImageResource(unSelectedIcon)
            showPrevious()
        }
        super.setSelected(selected)
    }
}