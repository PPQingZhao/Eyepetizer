package com.pp.library_base.base

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.pp.library_ui.utils.AppTheme
import com.pp.library_ui.utils.ViewTreeAppThemeViewModel
import com.pp.library_ui.utils.Theme
import com.pp.mvvm.BR
import com.pp.mvvm.LifecycleActivity

/**
 * theme fragment
 */
abstract class ThemeActivity<VB : ViewDataBinding, VM : ThemeViewModel> :
    LifecycleActivity<VB, VM>() {

    val mThemeViewModel = AppTheme()
    private val mViewThemes by lazy { mutableListOf<Theme>() }

    private fun applyTheme(lifecycle: Lifecycle, theme: Theme) {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                mViewThemes.remove(theme)
            }
        })
        mViewThemes.add(theme)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        applyTheme(lifecycle,mThemeViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // windowBackground 主题变化
        mThemeViewModel.windowBackground.observe(this) {
            window.setBackgroundDrawable(ColorDrawable(it ?: Color.TRANSPARENT))
        }

        ViewTreeAppThemeViewModel.set(mBinding.root,mThemeViewModel)
    }

    @CallSuper
    override fun onSetVariable(binding: VB, viewModel: VM): Boolean {
        // set theme variable
        mBinding.setVariable(BR.themeViewModel, mThemeViewModel)
        return super.onSetVariable(binding, viewModel)
    }

    override fun onApplyThemeResource(theme: Resources.Theme?, resid: Int, first: Boolean) {
        super.onApplyThemeResource(theme, resid, first)

//        Log.e("TAG", "onApplyThemeResource")
        theme?.apply {
            mViewThemes.forEach {
                it.setTheme(theme)
            }
        }
    }
}