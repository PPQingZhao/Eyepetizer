package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class PlayInfo(
    @SerializedName("bitRate")
    val bitRate: Int,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlList")
    val urlList: List<Url>,
    @SerializedName("videoId")
    val videoId: Int,
    @SerializedName("width")
    val width: Int
) {
    data class Url(
        @SerializedName("name")
        val name: String,
        @SerializedName("size")
        val size: Int,
        @SerializedName("url")
        val url: String
    )
}