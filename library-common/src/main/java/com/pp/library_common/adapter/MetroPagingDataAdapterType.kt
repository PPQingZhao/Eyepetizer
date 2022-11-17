package com.pp.library_common.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.DefaultBindingPagingDataAdapter
import com.pp.library_common.model.MetroFollowItemViewModel2
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.model.MetroSmallVideoCardItemViewModel
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.ItemFollowCardBindingImpl
import com.pp.library_ui.databinding.ItemToBeDevelopedBindingImpl
import com.pp.library_ui.databinding.ItemVideoCardBinding
import com.pp.library_ui.databinding.ItemVideoSmallCardBinding

object MetroPagingDataAdapterType {
    const val type_description_text = 0
    const val type_feed_item_detail = type_description_text + 1

    val DIFF_CALLBACK by lazy {

        object : DiffUtil.ItemCallback<Metro>() {
            override fun areItemsTheSame(oldItem: Metro, newItem: Metro): Boolean {
                return oldItem.metroId == newItem.metroId
            }

            override fun areContentsTheSame(oldItem: Metro, newItem: Metro): Boolean {
                return oldItem.metroData.resourceId == newItem.metroData.resourceId
            }

        }
    }

    /**
     * large video card
     */
    val largeVideoCardPagingDataAdapter by lazy {

        DefaultBindingPagingDataAdapter<ItemVideoCardBinding, MetroLargeVideoCardItemViewModel, Metro>(
            { _, item, cacheItemViewModel ->
                if (null != cacheItemViewModel) {
                    cacheItemViewModel.metro = item
                    cacheItemViewModel
                } else {
                    MetroLargeVideoCardItemViewModel(item)
                }
            },
            { parent, _, inflater ->
                ItemVideoCardBinding.inflate(inflater, parent, false)
            },
            DIFF_CALLBACK
        )
    }

    /**
     * small video card
     */
    val smallVideoCardPagingDataAdapter by lazy {

        DefaultBindingPagingDataAdapter<ItemVideoSmallCardBinding, MetroSmallVideoCardItemViewModel, Metro>(
            { _, item, cacheItemViewModel ->
                if (null != cacheItemViewModel) {
                    cacheItemViewModel.metro = item

                    cacheItemViewModel
                } else {
                    MetroSmallVideoCardItemViewModel(item)
                }
            },
            { parent, _, inflater ->
                ItemVideoSmallCardBinding.inflate(inflater, parent, false)
            },
            DIFF_CALLBACK
        )
    }

    fun description_text(layoutInflater: LayoutInflater) =
        DefaultViewBindingItem<Metro>(
            type_description_text,
            {
                it?.style?.tplLabel == EyepetizerService2.MetroType.Style.description_text
            },
            {
                val binding = ItemToBeDevelopedBindingImpl.inflate(layoutInflater, it, false)
                binding.root.visibility = View.GONE
                binding
            },
            { binding, item, cacheItemViewModel ->
                item?.metroData?.text
            })

    fun feed_item_detail(layoutInflater: LayoutInflater) =
        DefaultViewBindingItem<Metro>(
            type_feed_item_detail,
            {
                it?.style?.tplLabel == EyepetizerService2.MetroType.Style.feed_item_detail
            },
            {
                ItemFollowCardBindingImpl.inflate(layoutInflater, it, false)
            },
            { binding, item, cacheItemViewModel ->
                if (cacheItemViewModel is MetroFollowItemViewModel2) {
                    cacheItemViewModel.metro = item
                } else {
                    MetroFollowItemViewModel2(item)
                }
            })

}
