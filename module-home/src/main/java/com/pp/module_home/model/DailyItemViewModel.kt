package com.pp.module_home.model

import com.pp.module_home.api.bean.FeedBean


class DailyItemViewModel(private val item: FeedBean.Item?) {
    var content = "daily==>"

    init {
        content = "$content ${item?.type ?: "null"}"
    }
}