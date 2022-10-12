package com.pp.module_home.model

import com.pp.module_home.api.bean.FeedBean
import java.text.SimpleDateFormat


class DailyItemViewModel(val item: FeedBean.Item?) {
    var title: String?
    var category: String?
    var imagePath: String?
    var icon: String?
    var duration: String?

    init {
        val data = item?.data?.content?.data
        title = data?.title
        category = "${data?.author?.name} # ${data?.category}"
        imagePath = data?.cover?.feed
        icon = data?.author?.icon
        duration = format.format(data?.duration?.times(1000L))

    }

    companion object {
        val format = SimpleDateFormat("mm:ss")
    }
}