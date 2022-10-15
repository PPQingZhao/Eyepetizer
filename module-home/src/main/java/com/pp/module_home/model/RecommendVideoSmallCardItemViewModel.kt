package com.pp.module_home.model

import android.util.Log
import com.pp.module_home.api.bean.RecommendBean
import java.text.SimpleDateFormat

class RecommendVideoSmallCardItemViewModel(item: RecommendBean.Item?) :VideoSmallCardItemViewModel(item) {

    init {
        val data = item?.data
//        title = "${item?.data?.dataType}: ${data?.title}"
        title = data?.title
        category = "# ${data?.category}"
        imagePath = data?.cover?.feed
        Log.e("RecommendVideoSmallCard","${imagePath}")
        duration = format.format(data?.duration?.times(1000L))
    }
    companion object {
        val format by lazy { SimpleDateFormat("mm:ss") }
    }
}