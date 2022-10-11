package com.pp.module_home.model

import com.pp.library_network.eyepetizer.bean.recommend.Item

class RecommendItemViewModel(private val item: Item?) {
    var content = "recommend==>"

    init {
        content = "$content ${item?.type ?: "null"}"
    }
}