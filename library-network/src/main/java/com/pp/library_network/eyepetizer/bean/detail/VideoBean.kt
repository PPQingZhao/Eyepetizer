package com.pp.library_network.eyepetizer.bean.detail


import com.google.gson.annotations.SerializedName
import java.security.acl.Owner

data class VideoBean(
    @SerializedName("adExist")
    val adExist: Boolean,
    @SerializedName("count")
    val count: Int,
    @SerializedName("itemList")
    val itemList: List<Item>,
    @SerializedName("nextPageUrl")
    val nextPageUrl: String,
    @SerializedName("total")
    val total: Int,

    @SerializedName("id")
    val id: Long?,
    @SerializedName("headerImage")
    val headerImage: String?,
    @SerializedName("brief")
    val brief: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("shareLink")
    val shareLink: String?,
    @SerializedName("adTrack")
    val adTrack: Any?,
)