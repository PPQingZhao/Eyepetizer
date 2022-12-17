package com.pp.library_common.extension

import com.pp.library_common.data.DetailsData
import com.pp.library_common.model.MetroDataDetailsInfoViewModel
import com.pp.library_network.eyepetizer.bean.MetroDataBean

fun MetroDataDetailsInfoViewModel.update(value: MetroDataBean?) {

    icon.set(value?.author?.avatar?.url)
    author.set(value?.author?.nick)
    content.set(value?.text)
    collectionCount.set(value?.consumption?.likeCount.toString())
    realCollectionCount.set(value?.consumption?.collectionCount.toString())
    replyCount.set(value?.consumption?.commentCount.toString())

    val realLocation = value?.realLocation
    if (realLocation?.isEmpty() != false) {
        publish.set("")
    } else {
        publish.set("发布于 $realLocation")
    }

    var topic = ""
    value?.topics?.onEach {
        topic = "$topic ${it.title}"
    }

    tag.set(topic)
}

fun MetroDataDetailsInfoViewModel.update(value: DetailsData?) {

    icon.set(value?.icon)
    author.set(value?.nickName)
    content.set(value?.content)
    collectionCount.set(value?.collectionCount)
    realCollectionCount.set(value?.realCollectionCount)
    replyCount.set(value?.replyCount)

    val realLocation = value?.publish
    if (realLocation?.isEmpty() != false) {
        publish.set("")
    } else {
        publish.set("发布于 $realLocation")
    }


    tag.set(value?.tag)
}