package com.pp.library_base.adapter

import androidx.lifecycle.Lifecycle
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pp.library_ui.utils.OnErrorClickListener
import com.pp.library_ui.utils.StateView
import kotlinx.coroutines.flow.collectLatest

fun <VH : ViewHolder, adapter : PagingDataAdapter<*, VH>> adapter.onErrorListener(): OnErrorClickListener {
    return object : OnErrorClickListener {
        override fun onErrorCLick(e: Throwable) {
            retry()
        }
    }
}

suspend fun <VH : ViewHolder, adapter : PagingDataAdapter<*, VH>> adapter.attachStateView(stateView: StateView) {
    stateView.showLoading()
    loadStateFlow.collectLatest {
        if (itemCount > 0) {
            stateView.showContent()
            return@collectLatest
        }

        if (it.append.endOfPaginationReached) {
            stateView.showEmpty()
            return@collectLatest
        }

        when (val refresh = it.refresh) {
            is LoadState.Loading -> stateView.showLoading()
            is LoadState.Error -> stateView.showError(refresh.error)
            else -> {
                stateView.showContent()
            }
        }
    }
}

fun <VH : ViewHolder, adapter : PagingDataAdapter<*, VH>> adapter.attachRecyclerView(
    lifecycle: Lifecycle,
    recyclerView: RecyclerView
) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = withLoadStateFooter(
        DefaultLoadMoreStateAdapter(lifecycle, onErrorListener())
    )
}

suspend fun <VH : ViewHolder, adapter : PagingDataAdapter<*, VH>> adapter.attachRefreshView(
    refreshLayout: SwipeRefreshLayout
) {
    refreshLayout.setOnRefreshListener {
        refresh()
    }
    loadStateFlow.collectLatest {
        refreshLayout.isRefreshing = itemCount > 0 && it.refresh is LoadState.Loading
    }
}