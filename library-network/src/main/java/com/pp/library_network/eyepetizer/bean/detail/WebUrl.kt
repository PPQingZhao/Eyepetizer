package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class WebUrl(
    @SerializedName("forWeibo")
    val forWeibo: String,
    @SerializedName("raw")
    val raw: String
)