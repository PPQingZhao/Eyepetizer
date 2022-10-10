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
    fun getFragment(position: Int): Fragment {
        return factory.create(position)
    }

    /**
     * fragment factory
     */
    interface FragmentFactory {
        fun create(position: Int): Fragment
    }
}