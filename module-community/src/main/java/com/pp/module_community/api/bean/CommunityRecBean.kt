package com.pp.module_community.api.bean
import com.google.gson.annotations.SerializedName


data class CommunityRecBean(
    @SerializedName("adExist")
    val adExist: Boolean,
    @SerializedName("count")
    val count: Int,
    @SerializedName("itemList")
    val itemList: List<Item>,
    @SerializedName("nextPageUrl")
    val nextPageUrl: String,
    @SerializedName("total")
    val total: Int
) {
    data class Item(
        @SerializedName("adIndex")
        val adIndex: Int,
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("id")
        val id: Int,
        @SerializedName("tag")
        val tag: Any?,
        @SerializedName("trackingData")
        val trackingData: Any?,
        @SerializedName("type")
        val type: String
    )

    data class Data(
        @SerializedName("adTrack")
        val adTrack: Any?,
        @SerializedName("content")
        val content: Content?,
        @SerializedName("count")
        val count: Int?,
        @SerializedName("dataType")
        val dataType: String,
        @SerializedName("footer")
        val footer: Any?,
        @SerializedName("header")
        val header: Header?,
        @SerializedName("itemList")
        val itemList: List<ItemX>?
    )

    data class Content(
        @SerializedName("adIndex")
        val adIndex: Int,
        @SerializedName("data")
        val `data`: DataX,
        @SerializedName("id")
        val id: Int,
        @SerializedName("tag")
        val tag: Any?,
        @SerializedName("trackingData")
        val trackingData: Any?,
        @SerializedName("type")
        val type: String
    )

    data class Header(
        @SerializedName("actionUrl")
        val actionUrl: String,
        @SerializedName("followType")
        val followType: String,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("iconType")
        val iconType: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("issuerName")
        val issuerName: String,
        @SerializedName("labelList")
        val labelList: Any?,
        @SerializedName("showHateVideo")
        val showHateVideo: Boolean,
        @SerializedName("tagId")
        val tagId: Int,
        @SerializedName("tagName")
        val tagName: Any?,
        @SerializedName("time")
        val time: Long,
        @SerializedName("topShow")
        val topShow: Boolean
    )

    data class ItemX(
        @SerializedName("adIndex")
        val adIndex: Int,
        @SerializedName("data")
        val `data`: DataXX,
        @SerializedName("id")
        val id: Int,
        @SerializedName("tag")
        val tag: Any?,
        @SerializedName("trackingData")
        val trackingData: Any?,
        @SerializedName("type")
        val type: String
    )

    data class DataX(
        @SerializedName("addWatermark")
        val addWatermark: Boolean,
        @SerializedName("area")
        val area: String,
        @SerializedName("checkStatus")
        val checkStatus: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("collected")
        val collected: Boolean,
        @SerializedName("consumption")
        val consumption: Consumption,
        @SerializedName("cover")
        val cover: Cover,
        @SerializedName("createTime")
        val createTime: Long,
        @SerializedName("dataType")
        val dataType: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("duration")
        val duration: Int?,
        @SerializedName("height")
        val height: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("ifMock")
        val ifMock: Boolean,
        @SerializedName("latitude")
        val latitude: Double,
        @SerializedName("library")
        val library: String,
        @SerializedName("longitude")
        val longitude: Double,
        @SerializedName("owner")
        val owner: Owner,
        @SerializedName("playUrl")
        val playUrl: String?,
        @SerializedName("playUrlWatermark")
        val playUrlWatermark: String?,
        @SerializedName("privateMessageActionUrl")
        val privateMessageActionUrl: Any?,
        @SerializedName("reallyCollected")
        val reallyCollected: Boolean,
        @SerializedName("recentOnceReply")
        val recentOnceReply: RecentOnceReply?,
        @SerializedName("releaseTime")
        val releaseTime: Long,
        @SerializedName("resourceType")
        val resourceType: String,
        @SerializedName("selectedTime")
        val selectedTime: Any?,
        @SerializedName("source")
        val source: String,
        @SerializedName("status")
        val status: Int,
        @SerializedName("tags")
        val tags: List<Tag>?,
        @SerializedName("title")
        val title: String,
        @SerializedName("transId")
        val transId: Any?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("uid")
        val uid: Int,
        @SerializedName("updateTime")
        val updateTime: Long,
        @SerializedName("url")
        val url: String?,
        @SerializedName("urls")
        val urls: List<String>?,
        @SerializedName("urlsWithWatermark")
        val urlsWithWatermark: List<String>?,
        @SerializedName("validateResult")
        val validateResult: String,
        @SerializedName("validateStatus")
        val validateStatus: String,
        @SerializedName("validateTaskId")
        val validateTaskId: String?,
        @SerializedName("width")
        val width: Int
    )

    data class Consumption(
        @SerializedName("collectionCount")
        val collectionCount: Int,
        @SerializedName("realCollectionCount")
        val realCollectionCount: Int,
        @SerializedName("replyCount")
        val replyCount: Int,
        @SerializedName("shareCount")
        val shareCount: Int
    )

    data class Cover(
        @SerializedName("blurred")
        val blurred: Any?,
        @SerializedName("detail")
        val detail: String,
        @SerializedName("feed")
        val feed: String,
        @SerializedName("homepage")
        val homepage: Any?,
        @SerializedName("sharing")
        val sharing: Any?
    )

    data class Owner(
        @SerializedName("actionUrl")
        val actionUrl: String,
        @SerializedName("area")
        val area: Any?,
        @SerializedName("avatar")
        val avatar: String,
        @SerializedName("birthday")
        val birthday: Long?,
        @SerializedName("city")
        val city: String?,
        @SerializedName("country")
        val country: String,
        @SerializedName("cover")
        val cover: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("expert")
        val expert: Boolean,
        @SerializedName("followed")
        val followed: Boolean,
        @SerializedName("gender")
        val gender: String?,
        @SerializedName("ifPgc")
        val ifPgc: Boolean,
        @SerializedName("job")
        val job: String?,
        @SerializedName("library")
        val library: String,
        @SerializedName("limitVideoOpen")
        val limitVideoOpen: Boolean,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("registDate")
        val registDate: Long,
        @SerializedName("releaseDate")
        val releaseDate: Long,
        @SerializedName("uid")
        val uid: Int,
        @SerializedName("university")
        val university: String?,
        @SerializedName("userType")
        val userType: String
    )

    data class RecentOnceReply(
        @SerializedName("actionUrl")
        val actionUrl: String,
        @SerializedName("contentType")
        val contentType: Any?,
        @SerializedName("dataType")
        val dataType: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("nickname")
        val nickname: String
    )

    data class Tag(
        @SerializedName("actionUrl")
        val actionUrl: String,
        @SerializedName("adTrack")
        val adTrack: Any?,
        @SerializedName("bgPicture")
        val bgPicture: String,
        @SerializedName("childTagIdList")
        val childTagIdList: Any?,
        @SerializedName("childTagList")
        val childTagList: Any?,
        @SerializedName("communityIndex")
        val communityIndex: Int,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("haveReward")
        val haveReward: Boolean,
        @SerializedName("headerImage")
        val headerImage: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("ifNewest")
        val ifNewest: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("newestEndTime")
        val newestEndTime: Long?,
        @SerializedName("tagRecType")
        val tagRecType: String
    )

    data class DataXX(
        @SerializedName("actionUrl")
        val actionUrl: String,
        @SerializedName("adTrack")
        val adTrack: List<Any>?,
        @SerializedName("autoPlay")
        val autoPlay: Boolean?,
        @SerializedName("bgPicture")
        val bgPicture: String?,
        @SerializedName("dataType")
        val dataType: String,
        @SerializedName("description")
        val description: String?,
        @SerializedName("header")
        val header: HeaderX?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("label")
        val label: Label?,
        @SerializedName("labelList")
        val labelList: List<LabelX>?,
        @SerializedName("shade")
        val shade: Boolean?,
        @SerializedName("subTitle")
        val subTitle: String?,
        @SerializedName("title")
        val title: String
    )

    data class HeaderX(
        @SerializedName("actionUrl")
        val actionUrl: Any?,
        @SerializedName("cover")
        val cover: Any?,
        @SerializedName("description")
        val description: Any?,
        @SerializedName("font")
        val font: Any?,
        @SerializedName("icon")
        val icon: Any?,
        @SerializedName("id")
        val id: Int,
        @SerializedName("label")
        val label: Any?,
        @SerializedName("labelList")
        val labelList: Any?,
        @SerializedName("rightText")
        val rightText: Any?,
        @SerializedName("subTitle")
        val subTitle: Any?,
        @SerializedName("subTitleFont")
        val subTitleFont: Any?,
        @SerializedName("textAlign")
        val textAlign: String,
        @SerializedName("title")
        val title: Any?
    )

    data class Label(
        @SerializedName("card")
        val card: String,
        @SerializedName("detail")
        val detail: Any?,
        @SerializedName("text")
        val text: String
    )

    data class LabelX(
        @SerializedName("actionUrl")
        val actionUrl: Any?,
        @SerializedName("text")
        val text: String
    )
}