package com.pp.library_base.base

import androidx.fragment.app.Fragment

open class Pager(private val size: Int, private val factory: FragmentFactory) {

    /**
     * the size of viewpager fragment
     */
    fun size(): Int {
        return size
    }

    /**
     * create fragment
     */
    fun getFragment(positin: Int): Fragment {
        return factory.create(positin)
    }

    /**
     * fragment factory
     */
    interface FragmentFactory {
        fun create(position: Int): Fragment
    }
}