package com.pp.library_common.model

import android.util.Log
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoCardItemViewModel
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
class MetroLargeVideoCardItemViewModel(val metro: PageDataBean.Card.CardData.Body.Metro?) :
    VideoCardItemViewModel(metro) {
    private val resourceId: Int?
    private val resourceType: String?

    init {
        val metroData = metro?.metroData
        resourceId = metroData?.resourceId
        resourceType = metroData?.resourceType
        GlobalScope.launch(Dispatchers.IO) {
            val response = EyepetizerService2.api.getItemDetails(resourceId, resourceType)
            if (response.code != EyepetizerService2.ErrorCode.SUCCESS) {
                // todo: 测试
                this@MetroLargeVideoCardItemViewModel.title.set(response.message?.content)
                Log.e("TAG", "${response.message?.content}")
                // 取消协程
                cancel()
            } else {

                response.result.run {

                    Log.e("TAG", "${this.video.title}")
                    this@MetroLargeVideoCardItemViewModel.title.set(this.video.title)
                    this@MetroLargeVideoCardItemViewModel.category.set("# ${this.category.name}")
                    this@MetroLargeVideoCardItemViewModel.imagePath.set(this.video.cover.url)
                    this@MetroLargeVideoCardItemViewModel.icon.set(this.author.avatar.url)
                    this@MetroLargeVideoCardItemViewModel.duration.set(this.video.duration.text)

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