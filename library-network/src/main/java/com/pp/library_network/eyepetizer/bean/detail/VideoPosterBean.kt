package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class VideoPosterBean(
    @SerializedName("fileSizeStr")
    val fileSizeStr: String,
    @SerializedName("scale")
    val scale: Double,
    @SerializedName("url")
    val url: String
)