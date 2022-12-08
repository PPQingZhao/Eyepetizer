package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName

data class PlayCtrl(
    @SerializedName("autoplay")
    val autoplay: Boolean,
    @SerializedName("autoplay_times")
    val autoplayTimes: Int,
    @SerializedName("need_cellular")
    val needCellular: Boolean,
    @SerializedName("need_wifi")
    val needWifi: Boolean,
    @SerializedName("need_wifi_preload")
    val need_wifi_preload: Boolean
)