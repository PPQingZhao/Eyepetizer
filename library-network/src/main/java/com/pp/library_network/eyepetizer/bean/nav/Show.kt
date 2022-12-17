package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("sdk")
    val sdk: String
)