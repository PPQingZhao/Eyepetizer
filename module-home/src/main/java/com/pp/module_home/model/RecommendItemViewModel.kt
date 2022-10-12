package com.pp.module_home.model

import com.pp.module_home.api.bean.RecommendBean

class RecommendItemViewModel(private val item: RecommendBean.Item?) {
    var content = "recommend==>"

    init {
        content = "$content ${item?.type ?: "null"}"
    }
}