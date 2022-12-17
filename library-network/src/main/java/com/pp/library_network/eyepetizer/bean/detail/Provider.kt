package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class Provider(
    @SerializedName("alias")
    val alias: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String
)