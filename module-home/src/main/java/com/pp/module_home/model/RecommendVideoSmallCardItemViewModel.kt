package com.pp.module_home.model

import com.pp.library_ui.model.VideoSmallCardItemViewModel
import com.pp.module_home.api.bean.RecommendBean
import java.text.SimpleDateFormat

class RecommendVideoSmallCardItemViewModel(item: RecommendBean.Item?) :
    VideoSmallCardItemViewModel(item) {

    init {
        val data = item?.data
//        title = "${item?.data?.dataType}: ${data?.title}"
        title.set(data?.title)
        category.set("# ${data?.category}")
        imagePath.set(data?.cover?.feed)
//        Log.e("RecommendVideoSmallCard","${imagePath}")
        duration.set(format.format(data?.duration?.times(1000L)))
    }

    companion object {
        val format by lazy { SimpleDateFormat("mm:ss") }
    }
}