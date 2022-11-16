package com.pp.library_common.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoSmallCardItemViewModel

class MetroSmallVideoCardItemViewModel(item: PageDataBean.Card.CardData.Body.Metro?) :
    VideoSmallCardItemViewModel() {
    private var resourceId: Long? = null
    private var resourceType: String? = null
    var metro: PageDataBean.Card.CardData.Body.Metro? = null
        set(value) {
            field = value

            field?.run {
                resourceId = metroData.resourceId
                resourceType = metroData.resourceType

                title.set(metroData.title)
                category.set("${metroData.tags?.get(0)?.title}")
                imagePath.set(metroData.cover.url)
                duration.set(metroData.duration?.text)
            }
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
