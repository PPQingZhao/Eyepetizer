package com.pp.library_network.eyepetizer.bean.detail

import com.google.gson.annotations.SerializedName

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
    val consumption: Consumption?,
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
    val id: Long,
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
    @SerializedName("url")
    val url: String?,
    @SerializedName("urls")
    val urls: List<String>?,
    @SerializedName("owner")
    val owner: OwnerBean?,
)