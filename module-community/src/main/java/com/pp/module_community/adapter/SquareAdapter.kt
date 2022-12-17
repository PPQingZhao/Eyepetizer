package com.pp.module_community.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_ui.databinding.ItemBannerBinding
import com.pp.library_ui.databinding.ItemImageFollowCardBinding
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.library_ui.databinding.ItemVideoFollowCardBindingImpl
import com.pp.module_community.databinding.ItemFollowSmallVideoBinding
import com.pp.module_community.model.SquareBannerListViewModel
import com.pp.module_community.model.SquareImageLargeItemViewModel
import com.pp.module_community.model.SquareVideoLargeItemViewModel
import com.pp.module_community.model.SquareVideoSmallItemViewModel
import com.pp.module_community.respository.SquareType

class SquareAdapter :
    BindingPagingDataAdapter<ViewDataBinding, MultiItemEntity, MultiItemEntity>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MultiItemEntity>() {
            override fun areItemsTheSame(
                oldItem: MultiItemEntity, newItem: MultiItemEntity,
            ): Boolean {
                return if (oldItem is SquareBannerListViewModel && newItem is SquareBannerListViewModel) {
                    oldItem.card?.cardUniqueId == newItem.card?.cardUniqueId
                } else if (oldItem is SquareVideoSmallItemViewModel && newItem is SquareVideoSmallItemViewModel) {
                    oldItem.metro?.metroId == newItem.metro?.metroId
                } else if (oldItem is SquareImageLargeItemViewModel && newItem is SquareImageLargeItemViewModel) {
                    oldItem.metro?.metroId == newItem.metro?.metroId
                } else if (oldItem is SquareVideoLargeItemViewModel && newItem is SquareVideoLargeItemViewModel) {
                    return oldItem.resourceId == newItem.resourceId
                } else {
                    false
                }
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: MultiItemEntity, newItem: MultiItemEntity,
            ): Boolean {
                return if (oldItem is SquareBannerListViewModel && newItem is SquareBannerListViewModel) {
                    return oldItem == newItem
                } else if (oldItem is SquareVideoSmallItemViewModel && newItem is SquareVideoSmallItemViewModel) {
                    return oldItem.resourceId == newItem.resourceId
                } else if (oldItem is SquareImageLargeItemViewModel && newItem is SquareImageLargeItemViewModel) {
                    return oldItem.resourceId == newItem.resourceId
                } else if (oldItem is SquareVideoLargeItemViewModel && newItem is SquareVideoLargeItemViewModel) {
                    return oldItem.resourceId == newItem.resourceId
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
        cacheItemViewModel: MultiItemEntity?,
    ): MultiItemEntity {
        return item!!
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            SquareType.TYPE_BANNER_LIST -> {
                ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            SquareType.TYPE_VIDEO_SMALL -> {
                ItemFollowSmallVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            SquareType.TYPE_IMAGE_LARGE -> {
                ItemImageFollowCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            SquareType.TYPE_VIDEO_LARGE -> {
                ItemVideoFollowCardBindingImpl.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            else -> {
                ItemToBeDevelopedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }
    }
}