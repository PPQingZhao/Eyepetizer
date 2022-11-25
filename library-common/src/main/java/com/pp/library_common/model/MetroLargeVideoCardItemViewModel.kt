package com.pp.library_common.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoCardItemViewModel

class MetroLargeVideoCardItemViewModel(item: Metro?) :
    VideoCardItemViewModel() {
    private var resourceId: Long? = null
    private var resourceType: String? = null
    var metro: Metro? = null
        set(value) {
            field = value

            resourceId = value?.metroData?.resourceId
            resourceType = value?.metroData?.resourceType
            title.set(value?.metroData?.title)
            category.set("${value?.metroData?.author?.nick} ${value?.metroData?.tags?.get(0)?.title}")
            imagePath.set(value?.metroData?.cover?.url)
            icon.set(value?.metroData?.author?.avatar?.url)
            duration.set(value?.metroData?.duration?.text)
        }

    init {
        metro = item
    }

    override fun onVideo(view: View) {
        ARouter.getInstance()
            .build(RouterPath.VideoDetails.activity_video_details)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }

}