package com.pp.module_home.model

import com.pp.library_network.eyepetizer.bean.RecommendBean

class RecommendItemViewModel(private val item: RecommendBean.Item?) {
    var content = "recommend==>"

    init {
        content = "$content ${item?.type ?: "null"}"
    }
}