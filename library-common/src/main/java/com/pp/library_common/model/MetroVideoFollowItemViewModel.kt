package com.pp.library_common.model

import android.view.View
import com.pp.library_common.extension.intentToVideoDetails
import com.pp.library_common.extension.setMetro
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.FollowCardItemViewModel

open class MetroVideoFollowItemViewModel(
    item: Metro?,
    private val mine: Boolean = false,
) : FollowCardItemViewModel.VideoFollowCardItemViewModel() {

    companion object {
        private const val TAG = "MetroFollowItem"
    }

    var resourceId: Long? = null
    var resourceType: String? = null

    var metro: Metro? = null
    set(value) {
        field = value
        setMetro(value,mine)

        val metroData = value?.metroData
        resourceId = metroData?.resourceId
        resourceType = metroData?.resourceType

        cover.set(metroData?.video?.cover?.url)
        playUrl.set(metroData?.video?.playUrl)
    }

    init {
        this.metro = item
    }

    override fun onItemClick(view: View) {
        intentToVideoDetails(resourceId?:0, resourceType)
    }
}