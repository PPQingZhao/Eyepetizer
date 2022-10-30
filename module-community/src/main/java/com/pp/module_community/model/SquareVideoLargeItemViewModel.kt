package com.pp.module_community.model

import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_common.model.MetroFollowItemViewModel
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel
import com.pp.library_ui.model.VideoCardItemViewModel
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_LARGE

class SquareVideoLargeItemViewModel(val metro: PageDataBean.Card.CardData.Body.Metro?) :
    FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>(), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_LARGE

    var resourceId: Int?
    var resourceType: String?

    init {

        val metroData = metro?.metroData
        resourceId = metroData?.resourceId
        resourceType = metroData?.resourceType

        metroData?.let {
            cover.set(it.cover.url)
            Log.e("hhh", "cover.url: ${it.cover.url}")
            author.set(it.author?.nick)
            icon.set(it.author?.avatar?.url)
            content.set(it.title)
            category.set(it.tags?.get(0)?.title ?: "")
        }
        date.set(metro?.trackingData?.show?.getOrNull(0)?.data?.devReleaseTime)

        val isVideo = metro?.type == EyepetizerService2.MetroType.VIDEO

        adapter = Adapter().apply {
            setDataList(listOf(ImageVideoItemViewModel(cover, isVideo)))
        }

    }

    inner class Adapter :
        BindingAdapter<ItemImageVideoBinding, ImageVideoItemViewModel, ImageVideoItemViewModel>() {
        override fun createViewModel(
            binding: ItemImageVideoBinding,
            item: ImageVideoItemViewModel?,
            cacheItemViewModel: ImageVideoItemViewModel?
        ): ImageVideoItemViewModel? {
            return cacheItemViewModel ?: item
        }

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemImageVideoBinding {
            return ItemImageVideoBinding.inflate(layoutInflater, parent, false)
        }

    }

    override fun onVideo(view: View) {
        ARouter.getInstance()
            .build(RouterPath.VideoDetails.activity_video_details)
            .withInt("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }
}