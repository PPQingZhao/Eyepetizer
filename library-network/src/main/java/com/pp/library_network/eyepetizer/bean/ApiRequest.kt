package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName

data class ApiRequest(
    @SerializedName("params")
    val params: Map<String, String>,
    @SerializedName("url")
    val url: String
): java.io.Serializable