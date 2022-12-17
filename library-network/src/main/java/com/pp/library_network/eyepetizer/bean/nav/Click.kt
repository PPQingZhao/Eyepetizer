package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName

data class Click(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("sdk")
    val sdk: String
)