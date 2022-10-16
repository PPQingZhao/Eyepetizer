package com.pp.module_home.model

import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.module_home.api.bean.FollowBean.Item
import java.text.SimpleDateFormat


class FollowItemViewModel(item: Item?): FollowCardItemViewModel<Item>(item) {

    init {
        val contentData = item?.data?.content?.data
        icon = contentData?.author?.icon
        author = contentData?.author?.name
        date = format.format(contentData?.date)
        content = "     ${contentData?.description}"
        feed = contentData?.cover?.feed
        category = contentData?.category

        collectionCount.set(contentData?.consumption?.collectionCount.toString() ?: "0")
        realCollectionCount.set(contentData?.consumption?.realCollectionCount.toString() ?: "0")
        replyCount.set(contentData?.consumption?.replyCount.toString() ?: "0")
    }

    companion object {
        val format by lazy { SimpleDateFormat("yyyy.MM.dd") }
    }

}