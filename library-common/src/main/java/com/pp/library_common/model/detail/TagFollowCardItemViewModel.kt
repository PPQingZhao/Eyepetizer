package com.pp.library_common.model.detail

import android.view.View
import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import com.pp.library_ui.model.FollowCardItem2ViewModel
import com.pp.library_ui.utils.TimeUtils

class TagFollowCardItemViewModel(item: VideoBean.Item?): FollowCardItem2ViewModel() {

    var videoItem: VideoBean.Item? = null
        set(value) {
            field = value
            value?.data?.let {
                it.header?.let { header ->
                    icon.set(header.icon)
                    category.set(header.description)
                }

                it.content?.let { content ->
                    duration.set(TimeUtils.format(content.data.duration))
                    cover.set(content.data.cover.feed)
                    title.set(content.data.title)
                }

            }
        }
    init {
        videoItem = item
    }

    override fun onVideo(view: View) {
        super.onVideo(view)

    }

}