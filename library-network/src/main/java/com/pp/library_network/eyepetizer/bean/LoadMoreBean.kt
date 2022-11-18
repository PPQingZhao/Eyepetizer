package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class LoadMoreBean<Item>(
    @SerializedName("item_count")
    val itemCount: Int,
    @SerializedName("item_list")
    val itemList: List<Item>,
    @SerializedName("last_item_id")
    val lastItemId: Int
)