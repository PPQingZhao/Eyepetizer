package com.pp.library_network.eyepetizer.bean
import com.google.gson.annotations.SerializedName


data class DiscoveryBean(
    @SerializedName("adExist")
    val adExist: Boolean,
    @SerializedName("count")
    val count: Int,
    @SerializedName("itemList")
    val itemList: List<Item>,
    @SerializedName("nextPageUrl")
    val nextPageUrl: Any?,
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
        @SerializedName("actionUrl")
        val actionUrl: String?,
        @SerializedName("adTrack")
        val adTrack: Any?,
        @SerializedName("count")
        val count: Int?,
        @SerializedName("dataType")
        val dataType: String,
        @SerializedName("description")
        val description: String?,
        @SerializedName("expert")
        val expert: Boolean?,
        @SerializedName("follow")
        val follow: Follow?,
        @SerializedName("footer")
        val footer: Any?,
        @SerializedName("haveReward")
        val haveReward: Boolean?,
        @SerializedName("header")
        val header: Header?,
        @SerializedName("icon")
        val icon: String?,
        @SerializedName("iconType")
        val iconType: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("ifNewest")
        val ifNewest: Boolean?,
        @SerializedName("ifPgc")
        val ifPgc: Boolean?,
        @SerializedName("ifShowNotificationIcon")
        val ifShowNotificationIcon: Boolean?,
        @SerializedName("itemList")
        val itemList: List<ItemX>?,
        @SerializedName("medalIcon")
        val medalIcon: Boolean?,
        @SerializedName("newestEndTime")
        val newestEndTime: Any?,
        @SerializedName("rightText")
        val rightText: String?,
        @SerializedName("subTitle")
        val subTitle: Any?,
        @SerializedName("switchStatus")
        val switchStatus: Boolean?,
        @SerializedName("text")
        val text: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("uid")
        val uid: Int?
    )

    data class Follow(
        @SerializedName("followed")
        val followed: Boolean,
        @SerializedName("itemId")
        val itemId: Int,
        @SerializedName("itemType")
        val itemType: String
    )

    data class Header(
        @SerializedName("actionUrl")
        val actionUrl: Any?,
        @SerializedName("cover")
        val cover: Any?,
        @SerializedName("font")
        val font: String,
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
        val title: String
    )

    data class ItemX(
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

    data class DataX(
        @SerializedName("actionUrl")
        val actionUrl: String,
        @SerializedName("adTrack")
        val adTrack: List<Any>?,
        @SerializedName("autoPlay")
        val autoPlay: Boolean?,
        @SerializedName("dataType")
        val dataType: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("header")
        val header: HeaderX?,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("label")
        val label: Label?,
        @SerializedName("labelList")
        val labelList: List<Any>?,
        @SerializedName("shade")
        val shade: Boolean,
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
}