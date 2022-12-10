package com.pp.library_common.model.detail

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoSmallCardItemViewModel
import com.pp.library_ui.utils.TimeUtils

open class TagSmallVideoCardItem2ViewModel(item: VideoBean.Item?) :
    VideoSmallCardItemViewModel() {
    private var resourceId: Long? = null
    private var resourceType: String? = null
    var videoItem: VideoBean.Item? = null
        set(value) {
            field = value
            val data = value?.data

            resourceType = data?.resourceType

            title.set(data?.title)
            var tag = "#${data?.category}"

            category.set(tag)
            imagePath.set(data?.cover?.feed)
            duration.set(TimeUtils.format(data?.duration?: 0))
        }

    init {
        videoItem = item
    }

    override fun onVideo(view: View) {
        ARouter.getInstance()
            .build(RouterPath.VideoDetails.activity_video_details)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }

}
