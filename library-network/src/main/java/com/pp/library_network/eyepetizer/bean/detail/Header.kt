package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("actionUrl")
    val actionUrl: String,
    @SerializedName("cover")
    val cover: Any?,
    @SerializedName("description")
    val description: String,
    @SerializedName("font")
    val font: Any?,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("iconType")
    val iconType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: Any?,
    @SerializedName("labelList")
    val labelList: Any?,
    @SerializedName("rightText")
    val rightText: Any?,
    @SerializedName("showHateVideo")
    val showHateVideo: Boolean,
    @SerializedName("subTitle")
    val subTitle: Any?,
    @SerializedName("subTitleFont")
    val subTitleFont: Any?,
    @SerializedName("textAlign")
    val textAlign: String,
    @SerializedName("time")
    val time: Long?,
    @SerializedName("title")
    val title: String,
    @SerializedName("issuerName")
    val issuerName: String?,
)