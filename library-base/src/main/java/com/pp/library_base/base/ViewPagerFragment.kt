package com.pp.library_base.base

import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_base.base.helper.PagerFragmentHelper
import com.pp.mvvm.LifecycleFragment
import com.pp.mvvm.LifecycleViewModel

/**
 * viewpager fragment
 */
abstract class ViewPagerFragment<VB : ViewDataBinding, VM : LifecycleViewModel> :
    LifecycleFragment<VB, VM>() {
    val mHelper: PagerFragmentHelper by lazy {
        PagerFragmentHelper(
            this,
            getViewPager()
        )
    }


    abstract fun getViewPager(): ViewPager2

}