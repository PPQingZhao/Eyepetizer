package com.pp.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner

abstract class LifecycleFragment<VB : ViewDataBinding, VM : LifecycleViewModel> : Fragment() {

    abstract val mBinding: VB

    val mViewModel: VM by lazy { ViewModelProvider(this)[getModelClazz()] }

    abstract fun getModelClazz(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mViewModel)
        onSetVariable(mBinding, mViewModel)
    }

    open fun onSetVariable(binding: VB, viewModel: VM) {
        // set default variable
        try {
            mBinding.setVariable(BR.viewModel, mViewModel)
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
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

    private var alreadResume = false
    override fun onResume() {
        super.onResume()
        if (!alreadResume) {
            Log.e("TAG", "onFirstResume==> ${this}")
            onFirstResume()
            alreadResume = true
        }
    }

    open fun onFirstResume() {
    }

}