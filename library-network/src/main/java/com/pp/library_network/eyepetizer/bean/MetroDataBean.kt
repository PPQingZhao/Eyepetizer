package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MetroDataBean(
    @SerializedName("app_link")
    val appLink: String,
    @SerializedName("author")
    val author: Author,
    @SerializedName("category")
    val category: Category,
    @SerializedName("collected")
    val collected: Boolean,
    @SerializedName("comment_extra_tracking_fields")
    val commentExtraTrackingFields: CommentExtraTrackingFields,
    @SerializedName("consumption")
    val consumption: Consumption,
    @SerializedName("images")
    val images: List<ImageBean?>,
    @SerializedName("is_mine")
    val isMine: Boolean,
    @SerializedName("item_id")
    val itemId: String,
    @SerializedName("labels")
    val labels: List<Any>,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("link")
    val link: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("private_msg_link")
    val privateMsgLink: String,
    @SerializedName("publish_time")
    val publishTime: String,
    @SerializedName("raw_publish_time")
    val rawPublishTime: String,
    @SerializedName("real_location")
    val realLocation: String,
    @SerializedName("recommend_level")
    val recommendLevel: String,
    @SerializedName("resource_id")
    val resourceId: Long,
    @SerializedName("resource_type")
    val resourceType: String,
    @SerializedName("subtitles")
    val subtitles: List<Any>,
    @SerializedName("text")
    val text: String,
    @SerializedName("text_pgc")
    val textPgc: String,
    @SerializedName("topics")
    val topics: List<Topic>,
    @SerializedName("type")
    val type: String,
    @SerializedName("video")
    val video: Video,
    @SerializedName("tags")
    val tags: List<Tag>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("cover")
    val cover: Cover,
    @SerializedName("duration")
    val duration: Duration?,
    @SerializedName("icons")
    val icons: List<IconBean>?,
    @SerializedName("item_list")
    val itemList: List<Item>?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("limit_rows")
    val limitRows: Boolean,
) : Serializable {

    data class Tag(
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("title")
        val title: String
    )

    data class Author(
        @SerializedName("avatar")
        val avatar: Avatar,
        @SerializedName("description")
        val description: String,
        @SerializedName("followed")
        val followed: Boolean,
        @SerializedName("link")
        val link: String,
        @SerializedName("nick")
        val nick: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("uid")
        val uid: Int
    ) {
        data class Avatar(
            @SerializedName("img_info")
            val imgInfo: ImgInfo,
            @SerializedName("shape")
            val shape: String,
            @SerializedName("url")
            val url: String
        ) {
            data class ImgInfo(
                @SerializedName("height")
                val height: Int,
                @SerializedName("scale")
                val scale: Int,
                @SerializedName("width")
                val width: Int
            )
        }
    }

    data class Category(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    data class CommentExtraTrackingFields(
        @SerializedName("resource_id")
        val resourceId: String,
        @SerializedName("resource_type")
        val resourceType: String
    )

    data class Consumption(
        @SerializedName("collection_count")
        val collectionCount: Int,
        @SerializedName("comment_count")
        val commentCount: Int,
        @SerializedName("like_count")
        val likeCount: Int,
        @SerializedName("share_count")
        val shareCount: Int
    )

    data class Location(
        @SerializedName("area")
        val area: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("latitude")
        val latitude: Float,
        @SerializedName("longitude")
        val longitude: Float
    )

    data class Topic(
        @SerializedName("id")
        val id: Long,
        @SerializedName("link")
        val link: String,
        @SerializedName("title")
        val title: String
    )

    data class Video(
        @SerializedName("background")
        val background: Background,
        @SerializedName("cover")
        val cover: Cover,
        @SerializedName("description_editor")
        val descriptionEditor: String,
        @SerializedName("duration")
        val duration: Duration,
        @SerializedName("height")
        val height: Int,
        @SerializedName("play_ctrl")
        val playCtrl: PlayCtrl,
        @SerializedName("play_info")
        val playInfo: List<PlayInfo>,
        @SerializedName("play_url")
        val playUrl: String,
        @SerializedName("poster")
        val poster: Poster,
        @SerializedName("recommend_level")
        val recommendLevel: String,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("title")
        val title: String,
        @SerializedName("title_pgc")
        val titlePgc: String,
        @SerializedName("video_id")
        val videoId: String,
        @SerializedName("width")
        val width: Int
    ) {
        data class Background(
            @SerializedName("img_info")
            val imgInfo: ImgInfo,
            @SerializedName("url")
            val url: String
        ) {
            data class ImgInfo(
                @SerializedName("height")
                val height: Int,
                @SerializedName("scale")
                val scale: Double,
                @SerializedName("width")
                val width: Int
            )
        }

        data class PlayCtrl(
            @SerializedName("autoplay")
            val autoplay: Boolean,
            @SerializedName("autoplay_times")
            val autoplayTimes: Int,
            @SerializedName("need_cellular")
            val needCellular: Boolean,
            @SerializedName("need_wifi")
            val needWifi: Boolean
        )

        data class PlayInfo(
            @SerializedName("height")
            val height: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("url_list")
            val urlList: List<Url>,
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

        data class Poster(
            @SerializedName("cover_img")
            val coverImg: CoverImg,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("scale")
            val scale: Double,
            @SerializedName("size")
            val size: String,
            @SerializedName("url")
            val url: String
        ) {
            data class CoverImg(
                @SerializedName("img_info")
                val imgInfo: ImgInfo,
                @SerializedName("url")
                val url: String
            ) {
                data class ImgInfo(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("scale")
                    val scale: Double,
                    @SerializedName("width")
                    val width: Int
                )
            }
        }

        data class Tag(
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("title")
            val title: String
        )
    }

    data class Item(
        @SerializedName("cover")
        val cover: Cover,
//        @SerializedName("image_id")
//        val imageId: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("resource_id")
        val resourceId: Long,
        @SerializedName("resource_type")
        val resourceType: String,
        @SerializedName("title")
        val title: String
    )
}