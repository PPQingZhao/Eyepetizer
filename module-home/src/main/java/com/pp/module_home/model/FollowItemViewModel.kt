package com.pp.module_home.model

import com.pp.library_network.eyepetizer.bean.follow.Item


class FollowItemViewModel(private val item: Item?) {
    var content = "follow==>"

    init {
        content = "$content ${item?.type ?: "null"}"
    }
}