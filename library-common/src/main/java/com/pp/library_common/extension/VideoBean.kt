package com.pp.library_common.extension

import com.pp.library_common.data.DetailsData
import com.pp.library_network.eyepetizer.bean.detail.VideoBean

fun VideoBean.Item.createDetailsData(): DetailsData? {
    return this.data.content?.data?.run {
        DetailsData(
            playUrl = playUrl,
            content = this.description,
            icon = this.author?.icon,
            nickName = this.author?.name,
            publish = "",
            tag = "",
            collectionCount = this.consumption.collectionCount.toString(),
            realCollectionCount = this.consumption.realCollectionCount.toString(),
            replyCount = this.consumption.replyCount.toString()
        )
    }
}