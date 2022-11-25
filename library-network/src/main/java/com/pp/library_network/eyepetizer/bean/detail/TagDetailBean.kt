package com.pp.library_network.eyepetizer.bean.detail


import com.google.gson.annotations.SerializedName

data class TagDetailBean(
    @SerializedName("tabInfo")
    val tabInfo: TabInfo,
    @SerializedName("tagInfo")
    val tagInfo: TagInfo
) {
    data class TabInfo(
        @SerializedName("defaultIdx")
        val defaultIdx: Int,
        @SerializedName("tabList")
        val tabList: List<Tab>
    ) {
        data class Tab(
            @SerializedName("adTrack")
            val adTrack: Any?,
            @SerializedName("apiUrl")
            val apiUrl: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("nameType")
            val nameType: Int,
            @SerializedName("tabType")
            val tabType: Int
        )
    }

    data class TagInfo(
        @SerializedName("actionUrl")
        val actionUrl: Any?,
        @SerializedName("bgPicture")
        val bgPicture: String,
        @SerializedName("childTabList")
        val childTabList: Any?,
        @SerializedName("dataType")
        val dataType: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("follow")
        val follow: Follow,
        @SerializedName("headerImage")
        val headerImage: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("lookCount")
        val lookCount: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("recType")
        val recType: Int,
        @SerializedName("shareLinkUrl")
        val shareLinkUrl: String,
        @SerializedName("tagDynamicCount")
        val tagDynamicCount: Int,
        @SerializedName("tagFollowCount")
        val tagFollowCount: Int,
        @SerializedName("tagVideoCount")
        val tagVideoCount: Int
    ) {
        data class Follow(
            @SerializedName("followed")
            val followed: Boolean,
            @SerializedName("itemId")
            val itemId: Int,
            @SerializedName("itemType")
            val itemType: String
        )
    }
}