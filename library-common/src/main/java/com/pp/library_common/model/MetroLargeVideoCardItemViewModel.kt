package com.pp.library_common.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoCardItemViewModel
import io.reactivex.schedulers.Schedulers

class MetroLargeVideoCardItemViewModel(metro: PageDataBean.Card.CardData.Body.Metro?) :
    VideoCardItemViewModel(metro) {

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

                    this@MetroLargeVideoCardItemViewModel.title.set(this.video.title)
                    this@MetroLargeVideoCardItemViewModel.category.set(this.category.name)
                    this@MetroLargeVideoCardItemViewModel.imagePath.set(this.video.cover.url)
                    this@MetroLargeVideoCardItemViewModel.icon.set(this.author.avatar.url)
                    this@MetroLargeVideoCardItemViewModel.duration.set(this.video.duration.text)

                }
            }
    }

   override fun onVideo(view: View) {
       ARouter.getInstance().build(RouterPath.VideoDetails.activity_video_details).navigation()
   }

}