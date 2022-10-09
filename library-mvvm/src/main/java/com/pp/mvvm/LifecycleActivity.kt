package com.pp.mvvm

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager.LayoutParams
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner

abstract class LifecycleActivity<VB : ViewDataBinding, VM : LifecycleViewModel> :
    FragmentActivity() {
    protected val mBinding: VB by lazy {
        DataBindingUtil.inflate<VB>(
            LayoutInflater.from(this),
            getLayoutRes(),
            null,
            false
        )
    }

    val mViewModel by lazy { ViewModelProvider(this).get(getModelClazz()) }

    abstract fun getModelClazz(): Class<VM>

    abstract @LayoutRes
    fun getLayoutRes(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        lifecycle.addObserver(mViewModel)
        mBinding.setLifecycleOwner { this.lifecycle }
        ViewTreeLifecycleOwner.set(mBinding.root, this)
        ViewTreeViewModelStoreOwner.set(mBinding.root, this)
        mBinding.setVariable(BR.viewModel, mViewModel)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setTranslucent()
    }

    fun setTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}