package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName

data class HotQueriesBean(
    @SerializedName("item_list") val itemList: List<String>
)
