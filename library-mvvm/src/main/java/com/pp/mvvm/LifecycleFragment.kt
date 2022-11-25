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

    val mViewModel: VM by lazy { ViewModelProvider(this, getModelFactory())[getModelClazz()] }

    open fun getModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider.AndroidViewModelFactory(activity?.application!!)

    abstract fun getModelClazz(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mViewModel)
        setVariable(mBinding, mViewModel)
    }

    private fun setVariable(binding: VB, viewModel: VM) {
        if (!onSetVariable(binding, viewModel)) {
            // set default variable
            try {
                binding.setVariable(BR.viewModel, viewModel)
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }
        }
    }

    open fun onSetVariable(binding: VB, viewModel: VM): Boolean = false

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