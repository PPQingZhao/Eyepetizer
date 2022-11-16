package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class RelatedRecommendBean(
    @SerializedName("ad_item_list")
    val adItemList: List<Any>,
    @SerializedName("item_count")
    val itemCount: Int,
    @SerializedName("item_list")
    val itemList: List<MetroDataBean>,
    @SerializedName("item_per_page")
    val itemPerPage: Int
)