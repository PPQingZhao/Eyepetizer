package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class Consumption(
    @SerializedName("collectionCount")
    val collectionCount: Int,
    @SerializedName("playCount")
    val playCount: Int,
    @SerializedName("realCollectionCount")
    val realCollectionCount: Int,
    @SerializedName("replyCount")
    val replyCount: Int,
    @SerializedName("shareCount")
    val shareCount: Int
)