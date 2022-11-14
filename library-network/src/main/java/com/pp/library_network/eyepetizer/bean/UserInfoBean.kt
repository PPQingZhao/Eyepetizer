package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class UserInfoBean(
    @SerializedName("author_id")
    val authorId: Int,
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("birthday")
    val birthday: Int,
    @SerializedName("brief")
    val brief: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("collected_count")
    val collectedCount: Int,
    @SerializedName("constellation")
    val constellation: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("enable_share")
    val enableShare: Boolean,
    @SerializedName("expert")
    val expert: Boolean,
    @SerializedName("fans_count")
    val fansCount: Int,
    @SerializedName("fans_count_link")
    val fansCountLink: String,
    @SerializedName("featured_count")
    val featuredCount: Int,
    @SerializedName("follow_count")
    val followCount: Int,
    @SerializedName("follow_count_link")
    val followCountLink: String,
    @SerializedName("follow_page_label")
    val followPageLabel: String,
    @SerializedName("followed")
    val followed: Boolean,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("is_mine")
    val isMine: Boolean,
    @SerializedName("item_count")
    val itemCount: Int,
    @SerializedName("job")
    val job: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("medal_count")
    val medalCount: Int,
    @SerializedName("medal_count_link")
    val medalCountLink: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("nav_tabs")
    val navTabs: NavTabs,
    @SerializedName("nick")
    val nick: String,
    @SerializedName("organization_info")
    val organizationInfo: List<Any>,
    @SerializedName("private_msg_link")
    val privateMsgLink: String,
    @SerializedName("recommend_count")
    val recommendCount: Int,
    @SerializedName("shared_count")
    val sharedCount: Int,
    @SerializedName("show_follow_btn")
    val showFollowBtn: Boolean,
    @SerializedName("show_private_msg_btn")
    val showPrivateMsgBtn: Boolean,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("type")
    val type: String,
    @SerializedName("uid")
    val uid: Int,
    @SerializedName("university")
    val university: String,
    @SerializedName("watch_history_link")
    val watchHistoryLink: String
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

    data class NavTabs(
        @SerializedName("nav_item")
        val navItem: NavItem,
        @SerializedName("nav_list")
        val navList: List<Nav>,
        @SerializedName("style")
        val style: String
    ) {
        data class NavItem(
            @SerializedName("left")
            val left: List<Any>,
            @SerializedName("right")
            val right: List<Any>
        )

        data class Nav(
            @SerializedName("api_request")
            val apiRequest: ApiRequest,
            @SerializedName("child_nav")
            val childNav: ChildNav,
            @SerializedName("default_display")
            val defaultDisplay: Boolean,
            @SerializedName("force_refresh")
            val forceRefresh: Boolean,
            @SerializedName("icon_type")
            val iconType: String,
            @SerializedName("is_recommend")
            val isRecommend: Boolean,
            @SerializedName("page_label")
            val pageLabel: String,
            @SerializedName("page_type")
            val pageType: String,
            @SerializedName("page_url")
            val pageUrl: String,
            @SerializedName("page_url_parameter")
            val pageUrlParameter: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("url")
            val url: String
        ) {
            class ApiRequest

            data class ChildNav(
                @SerializedName("fixed")
                val fixed: List<Any>,
                @SerializedName("slide")
                val slide: List<Any>
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