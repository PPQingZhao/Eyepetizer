package com.pp.library_base.base.helper

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_base.base.Pager

/**
 * viewpager fragment
 */
class PagerFragmentHelper(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    val viewPager: ViewPager2,
) {
    private val adapter: FragmentAdapter
    lateinit var pager: Pager

    init {
        adapter = FragmentAdapter(manager, lifecycle)
    }

    constructor(
        activity: FragmentActivity,
        viewPager: ViewPager2,
    ) : this(activity.supportFragmentManager, activity.lifecycle, viewPager)

    constructor(
        fragment: Fragment,
        viewPager: ViewPager2,
    ) : this(fragment.childFragmentManager, fragment.lifecycle, viewPager)

    fun attach(pager: Pager): PagerFragmentHelper {
        this.pager = pager
        viewPager.adapter = adapter
        val firstChild = viewPager.getChildAt(0)
        if (firstChild is RecyclerView) {
            firstChild.overScrollMode = View.OVER_SCROLL_IF_CONTENT_SCROLLS
        }
        return this
    }

    /**
     * adapter for ViewPager2
     */
    inner class FragmentAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int {
            return pager.size()
        }

        override fun createFragment(position: Int): Fragment {
            return pager.getFragment(position)
        }
    }
}