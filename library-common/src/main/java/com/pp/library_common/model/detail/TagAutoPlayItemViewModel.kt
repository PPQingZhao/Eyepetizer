package com.pp.library_common.model.detail

import android.view.View
import com.pp.library_common.extension.intentToVideoDetails
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.library_ui.model.AutoPlayItemViewModel

class TagAutoPlayItemViewModel(item: Item?): AutoPlayItemViewModel() {
    private var resourceId: Long? = null
    private var resourceType: String? = null
    var videoItem: Item? = null
        set(value) {
            field = value

            resourceId = value?.data?.content?.data?.id
            resourceType = value?.data?.content?.data?.resourceType
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
                    dataX.consumption?.let { consumption ->
                        replyCount.set("${consumption.replyCount}")
                        collectionCount.set("${consumption.collectionCount}")
                        realCollectionCount.set("${consumption.realCollectionCount}")
                    }

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

    override fun onVideo(view: View) {

        intentToVideoDetails(resourceId ?: 0, resourceType)
    }
}