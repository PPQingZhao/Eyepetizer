package com.pp.module_community.model

import android.util.Log
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_SMALL

class SquareVideoSmallItemViewModel(val metro: PageDataBean.Card.CardData.Body.Metro?) :
    FollowVideoSmallItemViewModel(metro), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_SMALL

    var resourceId: Long = 0
    var resourceType: String = ""

    init {
        metro?.metroData?.let {
            resourceId = it.resourceId
            resourceType = it.resourceType
        }

        metro?.apply {
            imagePath.set(metroData.cover.url)
            description.set(metroData.title)
            metroData.author?.avatar?.url?.let {
                avatarPath.set(it)
            }

            nickname.set(metroData.author?.nick)
        }
    }

    override fun onVideo(view: View) {
        ARouter.getInstance()
            .build(RouterPath.VideoDetails.activity_video_details)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }

}