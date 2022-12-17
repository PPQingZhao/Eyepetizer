package com.pp.module_video_details.ui

import android.app.Application
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_common.model.MetroDataDetailsInfoViewModel
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.ImageBean

class ImageDetailsViewModel(app: Application) : ThemeViewModel(app) {

    val detailsModel = MetroDataDetailsInfoViewModel()

    suspend fun getImageDetails(
        resourceId: Long?,
        resourceType: String?,
    ): List<ImageBean>? {
        val itemDetails = EyepetizerService2.itemApi.getItemDetails(resourceId, resourceType)
        detailsModel.metro = itemDetails.result
        return itemDetails.result?.images
    }
}