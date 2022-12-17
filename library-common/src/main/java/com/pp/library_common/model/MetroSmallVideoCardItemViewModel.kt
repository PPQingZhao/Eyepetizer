package com.pp.library_common.model

import android.view.View
import com.pp.library_common.extension.intentToVideoDetails
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.VideoSmallCardItemViewModel

open class MetroSmallVideoCardItemViewModel(item: Metro?) :
    VideoSmallCardItemViewModel() {
    private var resourceId: Long? = null
    private var resourceType: String? = null
    var metro: Metro? = null
        set(value) {
            field = value
            val metroData = value?.metroData

            resourceId = metroData?.resourceId
            resourceType = metroData?.resourceType

            title.set(metroData?.title)
            var tag = ""
            value?.metroData?.tags?.forEach {
                tag = "${tag}${it.title} "
            }
            category.set(tag)
            imagePath.set(metroData?.cover?.url)
            duration.set(metroData?.duration?.text)
        }

    init {
        metro = item
    }

    override fun onVideo(view: View) {
        intentToVideoDetails(resourceId ?: 0, resourceType)
    }

}
