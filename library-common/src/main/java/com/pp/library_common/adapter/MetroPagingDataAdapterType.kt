package com.pp.library_common.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.DefaultBindingPagingDataAdapter
import com.pp.library_common.model.*
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.IconBean
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.*

object MetroPagingDataAdapterType {
    const val type_description_text = 0
    const val type_feed_item_detail = type_description_text + 1
    const val type_feed_cover_large_video = type_feed_item_detail + 1
    const val type_feed_cover_small_video = type_feed_cover_large_video + 1

    const val type_icon_grid = type_feed_cover_small_video + 1
    const val type_slide_cover_image_with_title = type_icon_grid + 1
    const val type_slide_cover_image = type_slide_cover_image_with_title + 1
    const val type_set_slide_metro_list = type_slide_cover_image + 1

    const val type_end_value = type_set_slide_metro_list

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
                    cacheItemViewModel
                } else {
                    MetroFollowItemViewModel2(item)
                }
            })

    fun feed_cover_large_video(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_feed_cover_large_video,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_large_video },
        { ItemVideoCardBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroLargeVideoCardItemViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else {
                MetroLargeVideoCardItemViewModel(item)
            }
        })

    fun feed_cover_small_video(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_feed_cover_small_video,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_small_video },
        { ItemVideoSmallCardBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroSmallVideoCardItemViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else MetroSmallVideoCardItemViewModel(item)
        })

    fun icon_grid(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_icon_grid,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.icon_grid },
        { ItemRecyclerBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroIconGridItemViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else MetroIconGridItemViewModel(item, binding.root.context)
        }
    )

    fun icon_item(layoutInflater: LayoutInflater) = DefaultViewBindingItem<IconBean>(
        type_icon_grid,
        { true },
        { ItemIconBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroIconItemViewModel) {
                cacheItemViewModel.icon = item
                cacheItemViewModel
            } else MetroIconItemViewModel(item)
        }
    )

    fun slide_cover_image_with_title(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_slide_cover_image_with_title,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.slide_cover_image_with_title },
        { ItemSlideCoverWithTitleBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroSlideCoverTitleItemViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else MetroSlideCoverTitleItemViewModel(item)
        }
    )

    fun slide_cover_image(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_slide_cover_image,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.slide_cover_image },
        { ItemSlideCoverBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroSlideCoverItemViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else MetroSlideCoverItemViewModel(item)
        }
    )

    fun set_slide_metro_list(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Card>(
        type_set_slide_metro_list,
        { it?.type == EyepetizerService2.CardType.SET_SLIDE_METRO_LIST },
        { ItemRecyclerBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is CardRecyclerViewModel) {
                cacheItemViewModel.card = item
                cacheItemViewModel
            } else CardRecyclerViewModel(item, binding.root.context)
        }
    )
}
