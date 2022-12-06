package com.pp.library_base.adapter

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pp.library_ui.utils.OnErrorClickListener

fun <VH:ViewHolder ,adapter:PagingDataAdapter<*, VH>> adapter.onErrorListener(): OnErrorClickListener {
    return object : OnErrorClickListener {
        override fun onErrorCLick(e: Throwable) {
            retry()
        }
    }
}
