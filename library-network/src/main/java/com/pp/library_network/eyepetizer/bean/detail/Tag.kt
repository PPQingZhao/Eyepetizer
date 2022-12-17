package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("actionUrl")
    val actionUrl: String,
    @SerializedName("adTrack")
    val adTrack: Any?,
    @SerializedName("alias")
    val alias: Any?,
    @SerializedName("bgPicture")
    val bgPicture: String,
    @SerializedName("childTagIdList")
    val childTagIdList: Any?,
    @SerializedName("childTagList")
    val childTagList: Any?,
    @SerializedName("communityIndex")
    val communityIndex: Int,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("haveReward")
    val haveReward: Boolean,
    @SerializedName("headerImage")
    val headerImage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ifNewest")
    val ifNewest: Boolean,
    @SerializedName("ifShow")
    val ifShow: Boolean,
    @SerializedName("level")
    val level: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("newestEndTime")
    val newestEndTime: Any?,
    @SerializedName("parentId")
    val parentId: Int,
    @SerializedName("recWeight")
    val recWeight: Double,
    @SerializedName("relationType")
    val relationType: Int,
    @SerializedName("tagRecType")
    val tagRecType: String,
    @SerializedName("tagStatus")
    val tagStatus: String,
    @SerializedName("top")
    val top: Int,
    @SerializedName("type")
    val type: String?
)