package com.pp.library_network.eyepetizer.bean.recommend

data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)