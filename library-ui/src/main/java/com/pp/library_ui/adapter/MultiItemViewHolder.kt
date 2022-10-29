package com.pp.library_ui.adapter

import androidx.databinding.ViewDataBinding

class MultiItemViewHolder<Data : Any?>(
    val viewBindingItem: ViewBindingItem<Data>,
    binding: ViewDataBinding
) :
    BindingHolder<ViewDataBinding>(binding)