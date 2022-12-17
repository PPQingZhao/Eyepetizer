package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class Cover(
    @SerializedName("blurred")
    val blurred: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("feed")
    val feed: String,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("sharing")
    val sharing: Any?
)