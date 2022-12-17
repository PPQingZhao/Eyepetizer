package com.pp.mvvm

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner

abstract class LifecycleActivity<VB : ViewDataBinding, VM : LifecycleViewModel> :
    AppCompatActivity() {
    abstract val mBinding: VB

    val mViewModel by lazy { ViewModelProvider(this,getModelFactory())[(getModelClazz())] }

    abstract fun getModelClazz(): Class<VM>

    open fun getModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider.AndroidViewModelFactory(application!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        lifecycle.addObserver(mViewModel)
        mBinding.setLifecycleOwner { this.lifecycle }
        ViewTreeLifecycleOwner.set(mBinding.root, this)
        ViewTreeViewModelStoreOwner.set(mBinding.root, this)

        setVariable(mBinding, mViewModel)
    }


    private fun setVariable(binding: VB, viewModel: VM) {
        try {
            if (!onSetVariable(binding, viewModel)) {
                // set default variable
                binding.setVariable(BR.viewModel, viewModel)
            }
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    open fun onSetVariable(binding: VB, viewModel: VM): Boolean = false

    private var windowInsets: WindowInsets? = null
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (customTheme())
            return

        setTranslucent()
        requireLightStatusBar(true)
        /*
            windowInsets分发:拦截WindowInsets, 将windowInsets分发给每个fragment
            沉浸式状态栏: window flags = LayoutParams.FLAG_TRANSLUCENT_STATUS 配合布局中 android:fitsSystemWindows="true"进行实现
        */
        (mBinding.root.parent as View).setOnApplyWindowInsetsListener { v, insets ->

//            Log.e("TAG", "setOnApplyWindowInsetsListener")
            windowInsets = WindowInsets(insets)

            mBinding.root.dispatchApplyWindowInsets(windowInsets)
//            }

            // 返回一个已消费的 windowInsets,结束分发流程
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowInsets.CONSUMED
            } else {
                insets.consumeSystemWindowInsets()
            }
        }

        /*
              fragment 布局创建完成时,分发windowInsets
         */
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                Log.e("TAG", "onFragmentViewCreated==>> ${f}")
                if (null == windowInsets) {
                    return
                }
                f.view?.let { view ->
                    ViewCompat.dispatchApplyWindowInsets(
                        view,
                        WindowInsetsCompat.toWindowInsetsCompat(WindowInsets(windowInsets))
                    )
                }
            }
        }, true)
    }

    protected open fun customTheme(): Boolean {
        return false
    }

    /**
     * 设置状态栏字体颜色
     * TODO 未做设配
     */
    fun requireLightStatusBar(light: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                if (light) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                if (light) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else View.VISIBLE
        }

    }

    private fun setTranslucent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

}