package com.pp.library_base.base

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class TabPager(
    var size: Int,
    var factory: FragmentFactory
) : Pager(size, factory) {
    private val mTabs by lazy { mutableListOf<Tab>() }

    fun initTabs(create: (Int) -> Tab): Unit {
        mTabs.clear()
        IntRange(1, size).forEachIndexed { index, i ->
            mTabs.add(create(index))
        }
    }

    fun getTab(position: Int): Tab {
        return mTabs.get(position)
    }

    class Tab(val tab: View?, @DrawableRes val icon: Int, @StringRes val text: Int) {

    }
}