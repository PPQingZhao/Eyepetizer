package com.pp.library_network.eyepetizer.bean.follow

data class FollowBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String,
    val total: Int
)