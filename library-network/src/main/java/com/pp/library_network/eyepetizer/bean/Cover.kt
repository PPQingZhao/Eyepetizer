package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName


data class Cover(
    @SerializedName("img_info")
    val imgInfo: ImgInfo,
    @SerializedName("url")
    val url: String
) {
    data class ImgInfo(
        @SerializedName("height")
        val height: Float,
        @SerializedName("scale")
        val scale: Double,
        @SerializedName("width")
        val width: Float
    )
}