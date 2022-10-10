package com.pp.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner

abstract class LifecycleFragment<VB : ViewDataBinding, VM : LifecycleViewModel> : Fragment() {
    val mBinding: VB by lazy {
        DataBindingUtil.inflate<VB>(
            layoutInflater,
            getLayoutRes(),
            null,
            false
        )
    }

    abstract @LayoutRes
    fun getLayoutRes(): Int

    val mViewModel: VM by lazy { ViewModelProvider(this)[getModelClazz()] }

    abstract fun getModelClazz(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mViewModel)
        mBinding.setVariable(BR.viewModel, mViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding.setLifecycleOwner {
            viewLifecycleOwner.lifecycle
        }

        val parent = mBinding.root.parent
        if (parent is ViewGroup) {
            parent.removeView(mBinding.root)
        }
        ViewTreeViewModelStoreOwner.set(mBinding.root, this)
        ViewTreeLifecycleOwner.set(mBinding.root, viewLifecycleOwner)
        return mBinding.root
    }

    var alreadResume = false
    override fun onResume() {
        super.onResume()
        if (!alreadResume) {
            onFirstResume()
            alreadResume = true
        }
    }

    open fun onFirstResume() {

    }
}