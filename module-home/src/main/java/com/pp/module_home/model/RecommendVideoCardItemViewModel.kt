package com.pp.module_home.model

import com.pp.library_ui.model.VideoCardItemViewModel
import com.pp.module_home.api.bean.RecommendBean
import java.text.SimpleDateFormat

class RecommendVideoCardItemViewModel(item: RecommendBean.Item?) : VideoCardItemViewModel(item) {

    init {
        val data = item?.data?.content?.data
//        title = "${item?.data?.dataType}: ${data?.title}"
        title = data?.title
        category = "${data?.author?.name} # ${data?.category}"
        imagePath = data?.cover?.feed
        icon = data?.author?.icon
        duration = format.format(data?.duration?.times(1000L))
    }
    companion object {
        val format by lazy { SimpleDateFormat("mm:ss") }
    }
}