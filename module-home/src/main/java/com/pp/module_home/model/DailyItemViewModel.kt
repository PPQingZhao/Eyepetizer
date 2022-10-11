package com.pp.module_home.model

import com.pp.library_network.eyepetizer.bean.feed.Item


class DailyItemViewModel(private val item: Item?) {
    var content = "daily==>"

    init {
        content = "$content ${item?.type ?: "null"}"
    }
}