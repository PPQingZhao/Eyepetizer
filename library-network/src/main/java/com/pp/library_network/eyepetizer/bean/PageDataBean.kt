package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName


data class PageDataBean(
    @SerializedName("card_list")
    val cardList: List<Card>,
    @SerializedName("float_entrance")
    val floatEntrance: List<Any>,
    @SerializedName("modal_list")
    val modalList: List<Modal>,
    @SerializedName("page_info")
    val pageInfo: PageInfo,
    @SerializedName("grant_type")
    val grant_type: String?,
    @SerializedName("server_timestamp")
    val server_timestamp: Long?,
)
