package com.pp.library_common.model.detail

import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import com.pp.library_ui.model.AutoPlayItemViewModel

class TagAutoPlayItemViewModel(item: VideoBean.Item?): AutoPlayItemViewModel() {

    var videoItem: VideoBean.Item? = null
        set(value) {
            field = value

            val bean = value
            bean?.data?.let {
                icon.set(it.author.icon)
                title.set(it.content?.data?.title)
                name.set(it.author.name)
                description.set(it.content?.data?.description)
                it.content?.tag?.forEachIndexed { index, tag ->
                    when (index) {
                        0 -> tag1.set(tag.name)
                        1 -> tag2.set(tag.name)
                        2 -> tag3.set(tag.name)
                    }
                }

                publishDate.set("${it.content?.data?.date}")
                imagePath.set(it.cover.feed)
                playUrl.set(it.playUrl)
                enablePlay.set(true)
            }

        }
    init {
        videoItem = item
    }
}