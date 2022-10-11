package com.pp.library_network.eyepetizer.bean.feed

data class FeedBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String,
    val total: Int
)