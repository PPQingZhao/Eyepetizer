package com.pp.library_common.model.detail

import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import com.pp.library_ui.model.HeaderItemViewModel

class TagTextCardViewModel(item: VideoBean.Item?): HeaderItemViewModel() {

    var videoItem: VideoBean.Item? = null
        set(value) {
            field = value
            leftText.set(value?.data?.text ?: "")
        }

    init {
        videoItem = item
    }
    override fun onMore() {

    }
}