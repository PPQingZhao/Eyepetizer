package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName

data class ChildNav(
    @SerializedName("fixed")
    val fixed: List<Any>,
    @SerializedName("slide")
    val slide: List<Any>
)