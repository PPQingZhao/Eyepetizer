package com.pp.library_common.model.detail

import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import com.pp.library_ui.model.AutoPlayItemViewModel

class TagAutoPlayItemViewModel(item: VideoBean.Item?): AutoPlayItemViewModel() {

    var videoItem: VideoBean.Item? = null
        set(value) {
            field = value

            val bean = value
            bean?.data?.let {
                icon.set(it.content?.data?.author?.icon)
                title.set(it.content?.data?.title)
                if (it.content?.data?.title == null) {
                    name.set(it.content?.data?.owner?.nickname)
                } else if (it.content?.data?.owner?.nickname == null) {
                    name.set(it.content?.data?.title)
                } else {
                    name.set(it.header?.issuerName)
                }
                description.set(it.content?.data?.description)
                it.content?.data?.tags?.forEachIndexed { index, tag ->
                    when (index) {
                        0 -> tag1.set(tag.name)
                        1 -> tag2.set(tag.name)
                        2 -> tag3.set(tag.name)
                    }
                }

                it.content?.data?.let { dataX ->
                    replyCount.set("${dataX.consumption.replyCount}")
                    collectionCount.set("${dataX.consumption.collectionCount}")
                    realCollectionCount.set("${dataX.consumption.realCollectionCount}")

                    val createTime = simpleFormat.format(dataX.createTime)
                    publishDate.set("$createTime 发布：")
                    imagePath.set(dataX.cover?.feed)
                    playUrl.set(dataX.playUrl)
                    enablePlay.set(true)
                }
            }

        }
    init {
        videoItem = item
    }
}