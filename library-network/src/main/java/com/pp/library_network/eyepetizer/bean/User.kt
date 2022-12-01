package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("is_mine")
    val isMine: Boolean,
    @SerializedName("link")
    val link: String,
    @SerializedName("nick")
    val nick: String,
    @SerializedName("uid")
    val uid: Int,
    @SerializedName("user_type")
    val userType: String
)