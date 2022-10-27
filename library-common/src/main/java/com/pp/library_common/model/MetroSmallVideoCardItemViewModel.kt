package com.pp.library_common.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoSmallCardItemViewModel
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
class MetroSmallVideoCardItemViewModel(metro: PageDataBean.Card.CardData.Body.Metro?) :
    VideoSmallCardItemViewModel(metro) {
    private val resourceId: Int?
    private val resourceType: String?

    init {
        val metroData = metro?.metroData
        resourceId = metroData?.resourceId
        resourceType = metroData?.resourceType

        GlobalScope.launch(Dispatchers.IO) {

            val it = EyepetizerService2.api.getItemDetails(resourceId, resourceType)

            if (it.code != EyepetizerService2.ErrorCode.SUCCESS) {
                // todo: 测试
                this@MetroSmallVideoCardItemViewModel.title.set(it.message?.content)
                cancel()
            } else {
                it.result.run {
                    this@MetroSmallVideoCardItemViewModel.title.set(this.video.title)
                    this@MetroSmallVideoCardItemViewModel.category.set("# ${this.category.name}")
                    this@MetroSmallVideoCardItemViewModel.imagePath.set(this.video.cover.url)
                    this@MetroSmallVideoCardItemViewModel.duration.set(this.video.duration.text)

                }
            }

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
