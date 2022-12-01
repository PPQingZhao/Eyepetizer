package com.pp.library_common.model

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.VideoCardItemViewModel

class MetroLargeVideoCard2ItemViewModel(item: Metro?) :
    MetroLargeVideoCardItemViewModel(item) {

    override fun onVideo(view: View) {
        ARouter.getInstance()
            .build(RouterPath.VideoDetails.activity_video_details)
            .withFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }

}