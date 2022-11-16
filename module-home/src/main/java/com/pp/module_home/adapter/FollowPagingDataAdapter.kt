package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_base.adapter.SelfLoadStateAdapter
import com.pp.library_common.model.MetroFollowItemViewModel
import com.pp.library_ui.databinding.ItemFollowCardBinding

class FollowPagingDataAdapter :
    BindingPagingDataAdapter<ItemFollowCardBinding, MetroFollowItemViewModel, MetroFollowItemViewModel>(
        DIFF_CALLBACK
    ) {

    companion object {
        const val TAG = "FollowAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MetroFollowItemViewModel>() {

            override fun areItemsTheSame(
                oldItem: MetroFollowItemViewModel,
                newItem: MetroFollowItemViewModel
            ) =
                oldItem.metro?.metroId == newItem.metro?.metroId

            override fun areContentsTheSame(
                oldItem: MetroFollowItemViewModel,
                newItem: MetroFollowItemViewModel
            ) = oldItem.metro?.metroData?.resourceId == newItem.metro?.metroData?.resourceId
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemFollowCardBinding {

        val binding = ItemFollowCardBinding.inflate(layoutInflater, parent, false)
        return binding

    }

    override fun createViewModel(
        binding: ItemFollowCardBinding,
        item: MetroFollowItemViewModel?,
        cacheItemViewModel: MetroFollowItemViewModel?
    ): MetroFollowItemViewModel? {
        return item
    }

    fun withLoadStateFooter(
        footer: SelfLoadStateAdapter<*>
    ): ConcatAdapter {
        addLoadStateListener { loadStates ->
            footer.loadStates = loadStates
        }
        return ConcatAdapter(this, footer)
    }

}