package com.pp.library_base.base

import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_base.base.helper.PagerFragmentHelper

/**
 * viewpager fragment
 */
abstract class ViewPagerFragment<VB : ViewDataBinding, VM : ThemeViewModel> :
    ThemeFragment<VB, VM>() {
    val mHelper: PagerFragmentHelper by lazy {
        PagerFragmentHelper(
            this,
            getViewPager()
        )
    }


    abstract fun getViewPager(): ViewPager2

}