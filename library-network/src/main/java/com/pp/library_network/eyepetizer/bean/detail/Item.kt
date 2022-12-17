package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("adIndex")
    val adIndex: Int,
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("id")
    val id: Int,
    @SerializedName("tag")
    val tag: Any?,
    @SerializedName("trackingData")
    val trackingData: Any?,
    @SerializedName("type")
    val type: String
)