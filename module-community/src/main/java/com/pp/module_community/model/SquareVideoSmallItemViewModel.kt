package com.pp.module_community.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_common.extension.isVideo
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_router_service.services.RouterPath
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_SMALL

class SquareVideoSmallItemViewModel(m: Metro?) :
    FollowVideoSmallItemViewModel(), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_SMALL

    var resourceId: Long = 0
    var resourceType: String = ""
    var metro: Metro? = null
        set(value) {
            field = value

            isVideo.set(metro?.metroData?.isVideo() == true)

            value?.metroData?.let {
                resourceId = it.resourceId
                resourceType = it.resourceType
            }

            value?.apply {
                imagePath.set(metroData?.cover?.url)
                description.set(metroData?.title)
                metroData?.author?.avatar?.url?.let {
                    avatarPath.set(it)
                }

                nickname.set(metroData?.author?.nick)
            }
        }

    init {

        this.metro = m
    }

    override fun onVideo(view: View) {
        if (isVideo.get()) {
            ARouter.getInstance()
                .build(RouterPath.VideoDetails.activity_video_details)
                .withLong("resourceId", resourceId ?: 0)
                .withString("resourceType", resourceType)
                .navigation()
        }
    }

}