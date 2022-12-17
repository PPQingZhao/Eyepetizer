package com.pp.library_common.adapter

import android.view.LayoutInflater
import com.pp.library_common.model.detail.TagAutoPlayItemViewModel
import com.pp.library_common.model.detail.TagFollowCardItemViewModel
import com.pp.library_common.model.detail.TagSmallVideoCardItem2ViewModel
import com.pp.library_common.model.detail.TagTextCardViewModel
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.databinding.ItemAutoPlayFollowCardBinding
import com.pp.library_ui.databinding.ItemFollowCard2Binding
import com.pp.library_ui.databinding.ItemFollowCard3Binding
import com.pp.library_ui.databinding.ItemHeaderBinding
import com.pp.library_ui.databinding.ItemVideoSmallCard2Binding

object VideoPagingDataAdapterType {

    const val type_load_more = 0
    const val type_text_card = type_load_more + 1
    const val type_follow_card = type_text_card + 1
    const val type_video_small_card = type_follow_card + 1
    const val type_auto_play_follow_card = type_video_small_card + 1
    const val type_picture_follow_card = type_auto_play_follow_card + 1

    fun type_text_card(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Item>(
        type_text_card,
        { it?.type == EyepetizerService.ItemType.textCard },
        { ItemHeaderBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is TagTextCardViewModel) {
                cacheItemViewModel.videoItem = item
                cacheItemViewModel
            } else {
                TagTextCardViewModel(item)
            }
        })

    fun type_follow_card(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Item>(
        type_follow_card,
        { it?.type == EyepetizerService.ItemType.followCard },
        { ItemFollowCard2Binding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is TagFollowCardItemViewModel) {
                cacheItemViewModel.videoItem = item
                cacheItemViewModel
            } else {
                TagFollowCardItemViewModel(item)
            }
        })

    fun type_video_small_card(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Item>(
        type_video_small_card,
        { it?.type == EyepetizerService.ItemType.videoSmallCard },
        { ItemVideoSmallCard2Binding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is TagSmallVideoCardItem2ViewModel) {
                cacheItemViewModel.videoItem = item
                cacheItemViewModel
            } else {
                TagSmallVideoCardItem2ViewModel(item)
            }
        })

    fun type_auto_play_follow_card(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Item>(
        type_auto_play_follow_card,
        { it?.type == EyepetizerService.ItemType.autoPlayFollowCard },
        { ItemAutoPlayFollowCardBinding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is TagAutoPlayItemViewModel) {
                cacheItemViewModel.videoItem = item
                cacheItemViewModel
            } else {
                TagAutoPlayItemViewModel(item)
            }
        })

    fun type_picture_follow_card(layoutInflater: LayoutInflater) = DefaultViewBindingItem<Item>(
        type_picture_follow_card,
        { it?.type == EyepetizerService.ItemType.pictureFollowCard },
        { ItemFollowCard3Binding.inflate(layoutInflater, it, false) },
        { binding, item, cacheItemViewModel ->
            if (cacheItemViewModel is TagFollowCardItemViewModel) {
                cacheItemViewModel.videoItem = item
                cacheItemViewModel
            } else {
                TagFollowCardItemViewModel(item)
            }
        })
}