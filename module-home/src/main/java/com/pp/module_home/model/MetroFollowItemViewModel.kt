package com.pp.module_home.model

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel


class MetroFollowItemViewModel(metro: PageDataBean.Card.CardData.Body.Metro?, context: Context) :
    FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    init {
        val metroData = metro?.metroData
        icon = metroData?.author?.avatar?.url
        author = metroData?.author?.nick
        content = metroData?.author?.description
        feed = metroData?.cover?.url
        category = metroData?.tags?.get(0)?.title?.split("#")?.get(1) ?: ""

        val trackingData = metro?.trackingData
        date = trackingData?.show?.get(0)?.data?.devReleaseTime

//        collectionCount.set(metroData?.consumption?.collectionCount.toString() ?: "0")
//        realCollectionCount.set(metroData?.consumption?.realCollectionCount.toString() ?: "0")
//        replyCount.set(metroData?.consumption?.replyCount.toString() ?: "0")

        layoutManager = LinearLayoutManager(context)
        adapter = Adapter().apply {
            setDataList(listOf(ImageVideoItemViewModel(feed, true)))
        }

    }

    inner class Adapter :
        BindingAdapter<ItemImageVideoBinding, ImageVideoItemViewModel, ImageVideoItemViewModel>() {
        override fun createViewModel(
            binding: ItemImageVideoBinding,
            item: ImageVideoItemViewModel?,
            cacheItemViewModel: ImageVideoItemViewModel?
        ): ImageVideoItemViewModel {
            return cacheItemViewModel ?: item ?: ImageVideoItemViewModel("")
        }

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemImageVideoBinding {
            return ItemImageVideoBinding.inflate(layoutInflater, parent, false)
        }

    }
}