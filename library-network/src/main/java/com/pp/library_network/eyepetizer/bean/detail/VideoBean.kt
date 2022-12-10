package com.pp.library_network.eyepetizer.bean.detail


import com.google.gson.annotations.SerializedName

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
    val total: Int
) {
    data class Item(
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
    ) {
        data class Content(
            @SerializedName("adIndex")
            val adIndex: Int,
            @SerializedName("data")
            val `data`: DataX,
            @SerializedName("id")
            val id: Int,
            @SerializedName("tag")
            val tag: List<Tag>?,
            @SerializedName("trackingData")
            val trackingData: Any?,
            @SerializedName("type")
            val type: String
        )

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
            val title: String
        )

        data class DataX(
            @SerializedName("content")
            val content: Content?,
            @SerializedName("header")
            val header: Header?,
            @SerializedName("text")
            val text: String?,

            @SerializedName("ad")
            val ad: Boolean,
            @SerializedName("adTrack")
            val adTrack: List<Any>,
            @SerializedName("areaSet")
            val areaSet: List<Any>,
            @SerializedName("author")
            val author: Author?,
            @SerializedName("brandWebsiteInfo")
            val brandWebsiteInfo: Any?,
            @SerializedName("campaign")
            val campaign: Any?,
            @SerializedName("candidateId")
            val candidateId: Int,
            @SerializedName("category")
            val category: String,
            @SerializedName("collected")
            val collected: Boolean,
            @SerializedName("consumption")
            val consumption: Consumption,
            @SerializedName("cover")
            val cover: Cover?,
            @SerializedName("createTime")
            val createTime: Long,
            @SerializedName("dataType")
            val dataType: String,
            @SerializedName("date")
            val date: Long?,
            @SerializedName("description")
            val description: String,
            @SerializedName("descriptionEditor")
            val descriptionEditor: String,
            @SerializedName("descriptionPgc")
            val descriptionPgc: String?,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("favoriteAdTrack")
            val favoriteAdTrack: Any?,
            @SerializedName("id")
            val id: Int,
            @SerializedName("idx")
            val idx: Int,
            @SerializedName("ifLimitVideo")
            val ifLimitVideo: Boolean,
            @SerializedName("infoStatus")
            val infoStatus: String,
            @SerializedName("label")
            val label: Any?,
            @SerializedName("labelList")
            val labelList: List<Any>,
            @SerializedName("lastViewTime")
            val lastViewTime: Any?,
            @SerializedName("library")
            val library: String,
            @SerializedName("playInfo")
            val playInfo: List<PlayInfo>,
            @SerializedName("playUrl")
            val playUrl: String,
            @SerializedName("played")
            val played: Boolean,
            @SerializedName("playlists")
            val playlists: Any?,
            @SerializedName("premiereDate")
            val premiereDate: Any?,
            @SerializedName("promotion")
            val promotion: Any?,
            @SerializedName("provider")
            val provider: Provider,
            @SerializedName("reallyCollected")
            val reallyCollected: Boolean,
            @SerializedName("recallSource")
            val recallSource: Any?,
            @SerializedName("releaseTime")
            val releaseTime: Long?,
            @SerializedName("remark")
            val remark: String?,
            @SerializedName("resourceType")
            val resourceType: String,
            @SerializedName("searchWeight")
            val searchWeight: Int,
            @SerializedName("shareAdTrack")
            val shareAdTrack: Any?,
            @SerializedName("showLabel")
            val showLabel: Boolean,
            @SerializedName("slogan")
            val slogan: String?,
            @SerializedName("sourceUrl")
            val sourceUrl: String,
            @SerializedName("src")
            val src: Any?,
            @SerializedName("status")
            val status: String,
            @SerializedName("subtitleStatus")
            val subtitleStatus: String,
            @SerializedName("subtitles")
            val subtitles: List<Any>,
            @SerializedName("tags")
            val tags: List<Tag>,
            @SerializedName("tailTimePoint")
            val tailTimePoint: Int,
            @SerializedName("thumbPlayUrl")
            val thumbPlayUrl: Any?,
            @SerializedName("title")
            val title: String,
            @SerializedName("titlePgc")
            val titlePgc: String?,
            @SerializedName("translateStatus")
            val translateStatus: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("updateTime")
            val updateTime: Long?,
            @SerializedName("videoPosterBean")
            val videoPosterBean: VideoPosterBean?,
            @SerializedName("waterMarks")
            val waterMarks: Any?,
            @SerializedName("webAdTrack")
            val webAdTrack: Any?,
            @SerializedName("webUrl")
            val webUrl: WebUrl,
        )

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

        data class Consumption(
            @SerializedName("collectionCount")
            val collectionCount: Int,
            @SerializedName("playCount")
            val playCount: Int,
            @SerializedName("realCollectionCount")
            val realCollectionCount: Int,
            @SerializedName("replyCount")
            val replyCount: Int,
            @SerializedName("shareCount")
            val shareCount: Int
        )

        data class Cover(
            @SerializedName("blurred")
            val blurred: String,
            @SerializedName("detail")
            val detail: String,
            @SerializedName("feed")
            val feed: String,
            @SerializedName("homepage")
            val homepage: String?,
            @SerializedName("sharing")
            val sharing: Any?
        )

        data class PlayInfo(
            @SerializedName("bitRate")
            val bitRate: Int,
            @SerializedName("dimension")
            val dimension: String,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("height")
            val height: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("size")
            val size: Int,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("urlList")
            val urlList: List<Url>,
            @SerializedName("videoId")
            val videoId: Int,
            @SerializedName("width")
            val width: Int
        ) {
            data class Url(
                @SerializedName("name")
                val name: String,
                @SerializedName("size")
                val size: Int,
                @SerializedName("url")
                val url: String
            )
        }

        data class Provider(
            @SerializedName("alias")
            val alias: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("status")
            val status: String
        )

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

        data class VideoPosterBean(
            @SerializedName("fileSizeStr")
            val fileSizeStr: String,
            @SerializedName("scale")
            val scale: Double,
            @SerializedName("url")
            val url: String
        )

        data class WebUrl(
            @SerializedName("forWeibo")
            val forWeibo: String,
            @SerializedName("raw")
            val raw: String
        )
    }
}