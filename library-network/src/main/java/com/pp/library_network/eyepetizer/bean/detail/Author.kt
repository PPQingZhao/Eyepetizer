package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("adTrack")
    val adTrack: Any?,
    @SerializedName("amplifiedLevelId")
    val amplifiedLevelId: Int?,
    @SerializedName("approvedNotReadyVideoCount")
    val approvedNotReadyVideoCount: Int,
    @SerializedName("authorStatus")
    val authorStatus: String,
    @SerializedName("authorType")
    val authorType: String,
    @SerializedName("cover")
    val cover: Any?,
    @SerializedName("description")
    val description: String,
    @SerializedName("expert")
    val expert: Boolean,
    @SerializedName("follow")
    val follow: Follow,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ifPgc")
    val ifPgc: Boolean,
    @SerializedName("latestReleaseTime")
    val latestReleaseTime: Long,
    @SerializedName("library")
    val library: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pendingVideoCount")
    val pendingVideoCount: Int,
    @SerializedName("recSort")
    val recSort: Int,
    @SerializedName("shield")
    val shield: Shield,
    @SerializedName("videoNum")
    val videoNum: Int
) {
    data class Follow(
        @SerializedName("followed")
        val followed: Boolean,
        @SerializedName("itemId")
        val itemId: Int,
        @SerializedName("itemType")
        val itemType: String
    )

    data class Shield(
        @SerializedName("itemId")
        val itemId: Int,
        @SerializedName("itemType")
        val itemType: String,
        @SerializedName("shielded")
        val shielded: Boolean
    )
}