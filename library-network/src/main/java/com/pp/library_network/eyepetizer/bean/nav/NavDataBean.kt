package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName

data class NavDataBean(
    @SerializedName("nav_item")
    val navItem: NavItem,
    @SerializedName("nav_list")
    val navList: List<Nav>,
    @SerializedName("style")
    val style: String
)