package com.pp.library_base.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemDefaultLoadStateFooterBinding

class DefaultLoadStateAdapter : LoadStateAdapter<BindingHolder<ItemDefaultLoadStateFooterBinding>>() {
    override fun onBindViewHolder(holder: BindingHolder<ItemDefaultLoadStateFooterBinding>, loadState: LoadState) {
        Log.e("DefaultLoadStateAdapter", loadState.toString())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BindingHolder<ItemDefaultLoadStateFooterBinding> {
        Log.e("DefaultLoadStateAdapter", loadState.toString())
        return BindingHolder(
            ItemDefaultLoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        Log.e("DefaultLoadStateAdapter", loadState.toString())
//        return loadState is LoadState.Loading
        return true;
    }


}