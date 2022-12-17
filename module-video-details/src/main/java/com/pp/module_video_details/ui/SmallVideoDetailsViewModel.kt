package com.pp.module_video_details.ui

import android.app.Application
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_common.data.DetailsData
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_common.model.MetroDataDetailsInfoViewModel

class SmallVideoDetailsViewModel(app: Application) : ThemeViewModel(app) {

    val detailsModel = MetroDataDetailsInfoViewModel()

    suspend fun getVideoDetails(
        resourceId: Long?,
        resourceType: String?,
    ): MetroDataBean.Video? {
        val itemDetails = EyepetizerService2.itemApi.getItemDetails(resourceId, resourceType)
        detailsModel.metro = itemDetails.result
        return itemDetails.result?.video
    }
}