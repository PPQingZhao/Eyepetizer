package com.pp.library_common.model

import android.view.View
import com.pp.library_common.extension.intentToVideoDetails
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.VideoCardItemViewModel

open class MetroLargeVideoCardItemViewModel(item: Metro?) :
    VideoCardItemViewModel() {
    protected var resourceId: Long? = null
    protected var resourceType: String? = null
    var metro: Metro? = null
        set(value) {
            field = value

            resourceId = value?.metroData?.resourceId
            resourceType = value?.metroData?.resourceType

            title.set(value?.metroData?.title)

            if (title.get()?.isEmpty() != false) {
                title.set(value?.metroData?.video?.title)
            }

            var tags = ""
            value?.metroData?.tags?.forEach {
                tags = "${tags}${it.title} "
            }

            if (tags.isEmpty()) {
                value?.metroData?.video?.tags?.forEach {
                    tags = "${tags}${it.title} "
                }
            }

            category.set("${value?.metroData?.author?.nick} ${tags}")

            imagePath.set(value?.metroData?.cover?.url)
            if (imagePath.get()?.isEmpty() != false) {
                imagePath.set(value?.metroData?.video?.cover?.url)
            }

            playUrl.set(value?.metroData?.playUrl)

            enablePlay.set(!(playUrl.get()?.isEmpty() ?: false))

            icon.set(value?.metroData?.author?.avatar?.url)
            duration.set(value?.metroData?.duration?.text)
            if (duration.get()?.isEmpty() != false) {
                duration.set(value?.metroData?.video?.duration?.text)
            }
        }

    init {
        metro = item
    }

    override fun onVideo(view: View) {
        intentToVideoDetails(resourceId?:0, resourceType)
    }

}