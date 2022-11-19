package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class IconBean(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("name")
    val name: String
)