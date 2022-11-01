package com.pp.library_base.adapter

import android.view.ViewGroup
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.RecyclerView

abstract class SelfLoadStateAdapter<VH : RecyclerView.ViewHolder>(
) : RecyclerView.Adapter<VH>() {

    var loadStates: CombinedLoadStates = CombinedLoadStates(
        LoadStates(
            LoadState.NotLoading(false),
            LoadState.NotLoading(false),
            LoadState.NotLoading(false)
        )
    )
        set(loadStates) {
            if (field.refresh != loadStates.refresh || field.append != loadStates.append) {
                val oldItem = displayLoadStateAsItem(field)
                val newItem = displayLoadStateAsItem(loadStates)

                if (oldItem && !newItem) {
                    notifyItemRemoved(0)
                } else if (newItem && !oldItem) {
                    notifyItemInserted(0)
                } else if (oldItem && newItem) {
                    notifyItemChanged(0)
                }
                field = loadStates
            }
        }

    open fun displayLoadStateAsItem(loadStates: CombinedLoadStates): Boolean {
        return loadStates.append is LoadState.Loading
                || loadStates.append is LoadState.Error
                || if (loadStates.append is LoadState.NotLoading) loadStates.append.endOfPaginationReached else false
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateViewHolder(parent, loadStates)
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, loadStates)
    }

    final override fun getItemViewType(position: Int): Int = getStateViewType(loadStates)

    final override fun getItemCount(): Int = if (displayLoadStateAsItem(loadStates)) 1 else 0

    /**
     * Called to create a ViewHolder for the given LoadState.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param loadState The LoadState to be initially presented by the new ViewHolder.
     *
     * @see [getItemViewType]
     * @see [displayLoadStateAsItem]
     */
    abstract fun onCreateViewHolder(parent: ViewGroup, loadState: CombinedLoadStates): VH

    /**
     * Called to bind the passed LoadState to the ViewHolder.
     *
     * @param loadState LoadState to display.
     *
     * @see [getItemViewType]
     * @see [displayLoadStateAsItem]
     */
    abstract fun onBindViewHolder(holder: VH, loadStates: CombinedLoadStates)

    open fun getStateViewType(loadState: CombinedLoadStates): Int = 0

}