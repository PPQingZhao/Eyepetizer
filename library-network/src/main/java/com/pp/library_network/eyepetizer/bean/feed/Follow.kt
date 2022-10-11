package com.pp.library_network.eyepetizer.bean.feed

data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)