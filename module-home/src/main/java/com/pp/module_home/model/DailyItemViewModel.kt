package com.pp.module_home.model

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoCardItemViewModel
import com.pp.module_home.api.bean.FeedBean
import java.text.SimpleDateFormat


class DailyItemViewModel(item: FeedBean.Item?): VideoCardItemViewModel(item){
    init {
        val data = item?.data?.content?.data
//        title = "${item?.data?.dataType}: ${data?.title}"
        title = data?.title
        category = "${data?.author?.name} # ${data?.category}"
        imagePath = data?.cover?.feed
        icon = data?.author?.icon
        duration = format.format(data?.duration?.times(1000L))

    }

    companion object {
        val format by lazy { SimpleDateFormat("mm:ss") }
    }

    override fun onVideo(view: View) {
        ARouter.getInstance().build(RouterPath.VideoDetails.activity_video_details).navigation()
    }
}