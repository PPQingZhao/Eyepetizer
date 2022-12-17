package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName

data class TrackingData(
    @SerializedName("click")
    val click: List<Click>,
    @SerializedName("show")
    val show: List<Show>
)