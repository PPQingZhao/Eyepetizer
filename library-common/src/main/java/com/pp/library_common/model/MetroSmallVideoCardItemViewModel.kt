package com.pp.library_common.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoSmallCardItemViewModel

class MetroSmallVideoCardItemViewModel(val metro: PageDataBean.Card.CardData.Body.Metro?) :
    VideoSmallCardItemViewModel(metro) {
    private val resourceId: Int?
    private val resourceType: String?

    init {
        val metroData = metro?.metroData
        resourceId = metroData?.resourceId
        resourceType = metroData?.resourceType

        metroData?.let {
            title.set(it.title)
            category.set("${it.tags?.get(0)?.title}")
            imagePath.set(it.cover.url)
            duration.set(it.duration?.text)
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
