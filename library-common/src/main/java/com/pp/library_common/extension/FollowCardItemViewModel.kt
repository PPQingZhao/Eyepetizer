package com.pp.library_common.extension

import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.R
import com.pp.library_ui.model.FollowCardItemViewModel

fun FollowCardItemViewModel.setMetro(value: Metro?, mine: Boolean) {
    val metroData = value?.metroData

    this.drawableFollow.set(
        if (mine && metroData?.isMine == true)
            R.drawable.ic_more_vert_24
        else if (metroData?.author?.followed == true || metroData?.isMine == true) R.drawable.layer_followed
        else R.drawable.layer_follow
    )

    this.icon.set(metroData?.author?.avatar?.url)
    this.author.set(metroData?.author?.nick)
    this.date.set(metroData?.rawPublishTime)
    this.area.set(metroData?.realLocation)
    this.content.set(metroData?.text)

    var topic = ""
    metroData?.topics?.forEach {
        topic = "${topic}${it.title} "
    }

    this.category.set(topic)
    this.collectionCount
        .set(metroData?.consumption?.likeCount.toString())
    this.realCollectionCount
        .set(metroData?.consumption?.collectionCount.toString())
    this.replyCount
        .set(metroData?.consumption?.commentCount.toString())
}