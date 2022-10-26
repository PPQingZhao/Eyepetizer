package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: Message?,
    @SerializedName("result")
    val result: T,
)

data class Message(
    @SerializedName("content")
    val content: String?,
    @SerializedName("action")
    val action: String?,
)
