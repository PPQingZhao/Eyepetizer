package com.pp.module_main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.pp.library_base.base.Pager
import com.pp.library_base.base.TabPager
import com.pp.library_base.base.TabPagerFragment
import com.pp.library_router_service.services.RouterPath
import com.pp.module_main.databinding.FragmentMainBinding
import com.pp.module_main.databinding.ViewTabBinding

class MainFragment : TabPagerFragment<FragmentMainBinding, MainViewModel>() {
    override val mBinding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }

    override fun getTabLayout(): TabLayout {
        return mBinding.mainTabLayout
    }

    override fun getViewPager(): ViewPager2 {
        mBinding.mainViewpager.isUserInputEnabled = false
//        mBinding.mainViewpager.offscreenPageLimit = 1
        return mBinding.mainViewpager
    }

    override fun getModelClazz(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    @SuppressLint("ClickableViewAccessibility")
    fun getPager(): TabPager {
        val factory = object : Pager.FragmentFactory {
            override fun create(position: Int): Fragment {
                return when (position) {
                    0 -> ARouter.getInstance().build(RouterPath.Home.fragment_home)
                        .navigation() as Fragment
                    1 -> ARouter.getInstance().build(RouterPath.Community.fragment_community)
                        .navigation() as Fragment
                    3 -> ARouter.getInstance().build(RouterPath.User.fragment_user)
                        .navigation() as Fragment

                    else -> TestFragment()
                }
            }
        }
        // tab 资源
        val resources = arrayListOf<Pair<Int, Int>>(
            R.drawable.selector_home_page to R.string.main_tab_pager,
            R.drawable.selector_community to R.string.main_tab_community,
            R.drawable.selector_notification to R.string.main_tab_notification,
            R.drawable.selector_mine to R.string.main_tab_mine
        )
        // 创建 pager
        val tabPager = TabPager(resources.size, factory)
        // 创建 tab
        tabPager.initTabs { position ->
            val tabBinding = DataBindingUtil.inflate<ViewTabBinding>(
                layoutInflater,
                R.layout.view_tab,
                null,
                false
            )
            val resourcePair = resources[position]
            tabBinding.root.setOnTouchListener { v, event ->

                if (event.action != MotionEvent.ACTION_DOWN) {
                    return@setOnTouchListener false
                }
                if (resourcePair.first != R.drawable.selector_mine) {
                    return@setOnTouchListener false
                }
                val postcard = ARouter.getInstance().build(RouterPath.User.activity_login)
                LogisticsCenter.completion(postcard)
                // 跳转登录
                loginLauncher.launch(
                    Intent(
                        context,
                        postcard.destination
                    )
                )

                true
            }

            val tab =
                TabPager.Tab(tabBinding.root, resourcePair.first, resourcePair.second);
            tabBinding.viewModel = tab
            tab
        }
        return tabPager
    }

    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != Activity.RESULT_OK){
                return@registerForActivityResult
            }
            mBinding.mainViewpager.currentItem = 3
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onFirstResume() {
        mHelper.attach(getPager(), false)
    }

}