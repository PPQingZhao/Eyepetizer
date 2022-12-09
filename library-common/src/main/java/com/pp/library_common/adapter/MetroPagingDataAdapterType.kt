package com.pp.library_common.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_base.adapter.DefaultBindingPagingDataAdapter
import com.pp.library_common.model.*
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.IconBean
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.*

object MetroPagingDataAdapterType {
    const val type_load_more = 0
    const val type_description_text = type_load_more + 1
    const val type_feed_item_detail = type_description_text + 1
    const val type_feed_cover_large_video = type_feed_item_detail + 1
    const val type_feed_cover_large_video2 = type_feed_cover_large_video + 1
    const val type_feed_cover_small_video = type_feed_cover_large_video2 + 1

    const val type_icon_grid = type_feed_cover_small_video + 1
    const val type_slide_cover_image_with_title = type_icon_grid + 1
    const val type_slide_cover_image = type_slide_cover_image_with_title + 1
    const val type_set_slide_metro_list = type_slide_cover_image + 1
    const val type_stacked_slide_cover_image = type_set_slide_metro_list + 1
    const val type_default_web = type_stacked_slide_cover_image + 1

    const val type_head_item = type_default_web + 1
    const val type_stacked_slide_cover_item = type_head_item + 1

    const val type_end_value = type_stacked_slide_cover_item

    val DIFF_CALLBACK by lazy {

        object : DiffUtil.ItemCallback<Metro>() {
            override fun areItemsTheSame(oldItem: Metro, newItem: Metro): Boolean {
                return oldItem.metroId == newItem.metroId
            }

            override fun areContentsTheSame(oldItem: Metro, newItem: Metro): Boolean {
                return oldItem.metroData?.resourceId == newItem.metroData?.resourceId
            }

        }
    }

    /**
     * 复用 RecycledViewPool
     */
    val recycledViewPool = RecyclerView.RecycledViewPool()

    /**
     * large video card
     */
    fun largeVideoCardPagingDataAdapter() =

        DefaultBindingPagingDataAdapter<ItemVideoCardBinding, MetroLargeVideoCardItemViewModel, Metro>(
            getItemViewType = { position -> type_feed_cover_large_video },
            onCreateViewModel = { _, item, cacheItemViewModel ->
                if (null != cacheItemViewModel) {
                    cacheItemViewModel.metro = item
                    cacheItemViewModel
                } else {
                    MetroLargeVideoCardItemViewModel(item)
                }
            },
            onCreateBinding = { parent, _, inflater ->
                ItemVideoCardBinding.inflate(inflater, parent, false)
            },
            diffCallback = DIFF_CALLBACK
        )

    /**
     * large video card2
     */
    fun largeVideoCard2PagingDataAdapter() =

        DefaultBindingPagingDataAdapter<ItemLargeVideoCard2Binding, MetroLargeVideoCard2ItemViewModel, Metro>(
            getItemViewType = { type_feed_cover_large_video2 },
            onCreateViewModel = { _, item, cacheItemViewModel ->
                if (null != cacheItemViewModel) {
                    cacheItemViewModel.metro = item
                    cacheItemViewModel
                } else {
                    MetroLargeVideoCard2ItemViewModel(item)
                }
            },
            onCreateBinding = { parent, _, inflater ->
                ItemLargeVideoCard2Binding.inflate(inflater, parent, false)
            },
            diffCallback = DIFF_CALLBACK
        )


    /**
     * small video card
     */
    fun smallVideoCardPagingDataAdapter() =

        DefaultBindingPagingDataAdapter<ItemVideoSmallCardBinding, MetroSmallVideoCardItemViewModel, Metro>(
            getItemViewType = { type_feed_cover_small_video },
            onCreateViewModel = { _, item, cacheItemViewModel ->
                if (null != cacheItemViewModel) {
                    cacheItemViewModel.metro = item
                    cacheItemViewModel
                } else {
                    MetroSmallVideoCardItemViewModel(item)
                }
            },
            onCreateBinding = { parent, _, inflater ->
                ItemVideoSmallCardBinding.inflate(inflater, parent, false)
            },
            diffCallback = DIFF_CALLBACK
        )


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

    fun feed_item_detail(layoutInflater: LayoutInflater, mine: Boolean = false) =
        DefaultViewBindingItem<Metro>(
            type_feed_item_detail,
            {
                it?.style?.tplLabel == EyepetizerService2.MetroType.Style.feed_item_detail
            },
            {
                ItemFollowCardBindingImpl.inflate(layoutInflater, it, false)
            },
            { binding, item, cacheItemViewModel ->
                if (cacheItemViewModel is MetroFollowItemViewModel) {
                    cacheItemViewModel.metro = item
                    cacheItemViewModel
                } else {
                    MetroFollowItemViewModel(item, mine)
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
        { ItemRecyclerWrapContentBinding.inflate(layoutInflater, it, false) },
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

    fun slide_cover_image_with_title(layoutInflater: LayoutInflater) =
        DefaultViewBindingItem<Metro>(
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

    fun stacked_slide_cover_image(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_stacked_slide_cover_image,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.stacked_slide_cover_image },
        { ItemRecyclerCardBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroCardRecyclerViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else MetroCardRecyclerViewModel(item, binding.root.context)
        }
    )

    fun stacked_slide_item(layoutInflater: LayoutInflater) =
        DefaultViewBindingItem<MetroDataBean.Item>(
            type_stacked_slide_cover_item,
            { true },
            { ItemStackedSlideCoverBinding.inflate(layoutInflater, it, false) },
            { binding, item, cacheItemViewModel ->
                if (cacheItemViewModel is MetroItemSlideCoverViewModel) {
                    cacheItemViewModel.metroItem = item
                    cacheItemViewModel
                } else MetroItemSlideCoverViewModel(item)
            }
        )

    fun default_web(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_default_web,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.default_web },
        { ItemWebviewBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroWebViewItemViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else MetroWebViewItemViewModel(item)
        }
    )

    fun head_item(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Card.CardData.Header>(
        type_head_item,
        { it is Card.CardData.Header },
        { ItemHeaderBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroHeadItemViewModel) {
                cacheItemViewModel.head = item
                cacheItemViewModel
            } else MetroHeadItemViewModel(item)
        }
    )

    fun feed_cover_large_video2(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Metro>(
        type_feed_cover_large_video2,
        { it?.style?.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_large_video },
        { ItemLargeVideoCard2Binding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is MetroLargeVideoCardItemViewModel) {
                cacheItemViewModel.metro = item
                cacheItemViewModel
            } else {
                MetroLargeVideoCard2ItemViewModel(item)
            }
        })

}
