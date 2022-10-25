package com.pp.module_home.model

import com.pp.library_ui.model.VideoCardItemViewModel
import com.pp.module_home.api.bean.RecommendBean
import java.text.SimpleDateFormat

class RecommendVideoCardItemViewModel(item: RecommendBean.Item?) : VideoCardItemViewModel(item) {

    init {
        val data = item?.data?.content?.data

        title.set(data?.title)
        category.set("${data?.author?.name} # ${data?.category}")
        imagePath.set(data?.cover?.feed)
        icon.set(data?.author?.icon)
        duration.set(DailyItemViewModel.format.format(data?.duration?.times(1000L)))
    }
    companion object {
        val format by lazy { SimpleDateFormat("mm:ss") }
    }
}