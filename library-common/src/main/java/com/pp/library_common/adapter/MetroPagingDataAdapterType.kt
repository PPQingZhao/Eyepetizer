package com.pp.library_common.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.DefaultBindingPagingDataAdapter
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.model.MetroSmallVideoCardItemViewModel
import com.pp.library_network.eyepetizer.bean.PageDataBean.Card.CardData.Body.Metro
import com.pp.library_ui.databinding.ItemVideoCardBinding
import com.pp.library_ui.databinding.ItemVideoSmallCardBinding

object MetroPagingDataAdapterType {
    private val DIFF_CALLBACK by lazy {

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

}
