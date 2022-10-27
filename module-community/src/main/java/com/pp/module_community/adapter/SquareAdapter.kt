package com.pp.module_community.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.module_community.databinding.ItemBannerListBinding
import com.pp.module_community.databinding.ItemFollowSmallVideoBinding
import com.pp.module_community.model.*
import com.pp.module_community.respository.SquareType

class SquareAdapter :
    BindingPagingDataAdapter<ViewDataBinding, MultiItemEntity, MultiItemEntity>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MultiItemEntity>() {
            override fun areItemsTheSame(
                oldItem: MultiItemEntity, newItem: MultiItemEntity
            ): Boolean {
                return if (oldItem is BannerListViewModel && newItem is BannerListViewModel) {
                    oldItem.card.cardUniqueId == newItem.card.cardUniqueId
                } else if (oldItem is SquareVideoSmallItemViewModel && newItem is SquareVideoSmallItemViewModel) {
                    oldItem.metro?.metroId == newItem.metro?.metroId
                } else if (oldItem is SquareVideoLargeItemViewModel && newItem is SquareVideoLargeItemViewModel) {
                    oldItem.metro?.metroId == newItem.metro?.metroId
                } else {
                    false
                }
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: MultiItemEntity, newItem: MultiItemEntity
            ): Boolean {
                return if (oldItem is BannerListViewModel && newItem is BannerListViewModel) {
                    return oldItem == newItem
                } else if (oldItem is SquareVideoSmallItemViewModel && newItem is SquareVideoSmallItemViewModel) {
                    return oldItem == newItem
                } else if (oldItem is SquareVideoLargeItemViewModel && newItem is SquareVideoLargeItemViewModel) {
                    return oldItem == newItem
                } else {
                    false
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.itemType ?: SquareType.DEVELOP
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: MultiItemEntity?,
        cacheItemViewModel: MultiItemEntity?
    ): MultiItemEntity {
        return cacheItemViewModel ?: item ?: SquareVideoSmallItemViewModel(null)
        /*return cacheItemViewModel ?:when (binding) {
            is ItemFollowSmallVideoBinding -> {
                SquareVideoSmallItemViewModel(null)
            }
            else -> {
                SquareVideoSmallItemViewModel(null)
            }
        }*/
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            SquareType.TYPE_BANNER_LIST -> {
                ItemBannerListBinding.inflate(layoutInflater, parent, false)
            }
            SquareType.TYPE_VIDEO_SMALL -> {
                ItemFollowSmallVideoBinding.inflate(layoutInflater, parent, false)
            }
            SquareType.TYPE_VIDEO_LARGE -> {
                ItemBannerListBinding.inflate(layoutInflater, parent, false)
            }
            else -> {
                ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
            }
        }
    }
}