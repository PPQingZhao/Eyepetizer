package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class LoginBean(
    @SerializedName("user_info")
    val userInfo: UserInfo
) {
    data class UserInfo(
        @SerializedName("avatar")
        val avatar: Avatar,
        @SerializedName("description")
        val description: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("nick")
        val nick: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("uid")
        val uid: Int
    ) {
        data class Avatar(
            @SerializedName("img_info")
            val imgInfo: ImgInfo,
            @SerializedName("url")
            val url: String
        ) {
            data class ImgInfo(
                @SerializedName("height")
                val height: Int,
                @SerializedName("scale")
                val scale: Int,
                @SerializedName("width")
                val width: Int
            )
        }
    }

}