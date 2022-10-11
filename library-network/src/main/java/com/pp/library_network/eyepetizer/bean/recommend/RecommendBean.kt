package com.pp.library_network.eyepetizer.bean.recommend

data class RecommendBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String,
    val total: Int
)