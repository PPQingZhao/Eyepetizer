package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName

data class NavItem(
    @SerializedName("center")
    val center: List<Any>,
    @SerializedName("left")
    val left: List<Any>,
    @SerializedName("right")
    val right: List<Any>
)