package com.pp.library_common.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoSmallCardItemViewModel
import io.reactivex.schedulers.Schedulers

class MetroSmallVideoCardItemViewModel(metro: PageDataBean.Card.CardData.Body.Metro?) :
    VideoSmallCardItemViewModel(metro) {

    init {
        val metroData = metro?.metroData

        EyepetizerService2.api.getItemDetails(metroData?.resourceId, metroData?.resourceType)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {

                if (it.code != EyepetizerService2.ErrorCode.SUCCESS) {
                    return@subscribe
                }
                it.result.run {

                    this@MetroSmallVideoCardItemViewModel.title.set(this.video.title)
                    this@MetroSmallVideoCardItemViewModel.category.set(this.category.name)
                    this@MetroSmallVideoCardItemViewModel.imagePath.set(this.video.cover.url)
                    this@MetroSmallVideoCardItemViewModel.duration.set(this.video.duration.text)

                }
            }


    }

    override fun onVideo(view: View) {
        ARouter.getInstance().build(RouterPath.VideoDetails.activity_video_details).navigation()
    }

}