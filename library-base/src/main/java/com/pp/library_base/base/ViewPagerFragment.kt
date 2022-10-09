package com.pp.library_base.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.pp.mvvm.LifecycleFragment
import com.pp.mvvm.LifecycleViewModel

/**
 * viewpager fragment
 */
abstract class ViewPagerFragment<VB : ViewDataBinding, VM : LifecycleViewModel> :
    LifecycleFragment<VB, VM>() {
    val mViewPager: ViewPager2 by lazy { getViewPager() }
    val mPager: Pager by lazy { getPager() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewPager.adapter = FragmentAdapter(this)

    }

    /**
     * 获取fragment pager
     */
    abstract fun getPager(): Pager

    abstract fun getViewPager(): ViewPager2

    /**
     * adapter for ViewPager2
     */
    inner class FragmentAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
        override fun getItemCount(): Int {
            return mPager.size()
        }

        override fun createFragment(position: Int): Fragment {
            return mPager.getFragment(position)
        }
    }
}