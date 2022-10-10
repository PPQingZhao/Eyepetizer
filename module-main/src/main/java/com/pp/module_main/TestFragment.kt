package com.pp.module_main

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.pp.mvvm.LifecycleFragment
import com.pp.mvvm.LifecycleViewModel

class TestFragment : LifecycleFragment<ViewDataBinding, LifecycleViewModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_test
    }

    override fun getModelClazz(): Class<LifecycleViewModel> {
        return LifecycleViewModel::class.java
    }

    override fun onFirstResume() {
        Log.e("TAG", "first resume")
    }
}