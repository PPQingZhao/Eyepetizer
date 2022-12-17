package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("click_action")
    val clickAction: String,
    @SerializedName("click_action_url")
    val clickActionUrl: String,
    @SerializedName("click_name")
    val clickName: String,
    @SerializedName("element_id")
    val elementId: Int,
    @SerializedName("element_label")
    val elementLabel: String,
    @SerializedName("element_title")
    val elementTitle: String,
    @SerializedName("element_type")
    val elementType: String,
    @SerializedName("relative_index")
    val relativeIndex: Int
)