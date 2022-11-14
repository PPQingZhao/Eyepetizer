package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("Cookie")
    val cookie: String,
    @SerializedName("Host")
    val host: String,
    @SerializedName("User-Agent")
    val userAgent: String,
    @SerializedName("x-api-key")
    val xApiKey: String,
    @SerializedName("X-THEFAIR-APPID")
    val xTHEFAIRAPPID: String,
    @SerializedName("X-THEFAIR-AUTH")
    val xTHEFAIRAUTH: String,
    @SerializedName("X-THEFAIR-CID")
    val xTHEFAIRCID: String,
    @SerializedName("X-THEFAIR-UA")
    val xTHEFAIRUA: String
)