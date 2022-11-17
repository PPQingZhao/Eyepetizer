package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class LoadMorePageBean(
    @SerializedName("item_count")
    val itemCount: Int,
    @SerializedName("item_list")
    val itemList: List<Metro>,
    @SerializedName("last_item_id")
    val lastItemId: Int
)