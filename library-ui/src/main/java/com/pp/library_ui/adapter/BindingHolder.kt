package com.pp.library_ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BindingHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)