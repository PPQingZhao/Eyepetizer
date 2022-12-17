package com.pp.library_common.model.detail

import android.view.View
import com.pp.library_common.extension.intentToImageDetails
import com.pp.library_common.extension.intentToVideoDetails
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.library_ui.model.FollowCardItem2ViewModel
import com.pp.library_ui.utils.TimeUtils

class TagFollowCardItemViewModel(item: Item?): FollowCardItem2ViewModel() {
    private var resourceId: Long? = null
    private var resourceType: String? = null
    var videoItem: Item? = null
        set(value) {
            field = value

            resourceId = value?.data?.content?.data?.id
            resourceType = value?.data?.content?.data?.resourceType
            value?.data?.let {
                it.header?.let { header ->
                    icon.set(header.icon)
                    category.set(header.description)
                    author.set(header.issuerName)
                }

                it.content?.let { content1 ->
                    duration.set(TimeUtils.format(content1.data.duration))
                    cover.set(content1.data.cover?.feed)
                    title.set(content1.data.title)

                    content.set(content1.data.description)
                    val createDate = simpleFormat.format(content1.data.createTime)
                    date.set("$createDate 发布：")

                    content1.data.urls?.let { urlList ->
                        urls.set(urlList)
                    }
                    if (content1.data.urls == null) {
                        content1.data.url?.let { url ->
                            urls.set(mutableListOf(url))
                        }
                    }

                    content1.data.consumption?.let { consumption ->
                        replyCount.set("${consumption.replyCount}")
                        collectionCount.set("${consumption.collectionCount}")
                        realCollectionCount.set("${consumption.realCollectionCount}")
                    }

                }

                it.content?.data?.tags?.forEachIndexed { index, tag ->
                    when (index) {
                        0 -> tag1.set(tag.name)
                        1 -> tag2.set(tag.name)
                        2 -> tag3.set(tag.name)
                    }
                }
            }
        }

    init {
        videoItem = item
    }

    override fun onVideo(view: View) {

        videoItem?.data?.content?.apply {
            if (type == "video") {
                intentToVideoDetails(resourceId ?: 0, resourceType)
            } else {
                intentToImageDetails(resourceId ?: 0, resourceType)
            }
        }
    }

}