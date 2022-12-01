package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("at_user")
    val atUser: User,
    @SerializedName("be_favorite")
    val beFavorite: Boolean,
    @SerializedName("can_delete")
    val canDelete: Boolean,
    @SerializedName("comment_content")
    val commentContent: String,
    @SerializedName("comment_id")
    val commentId: String,
    @SerializedName("comment_time")
    val commentTime: String,
    @SerializedName("count_summary")
    val countSummary: CountSummary,
    @SerializedName("location")
    val location: String,
    @SerializedName("parent_id")
    val parentId: String,
    @SerializedName("reply_count")
    val replyCount: Int,
    @SerializedName("reply_list")
    val replyList: List<Comment>,
    @SerializedName("resource_id")
    val resourceId: String,
    @SerializedName("resource_type")
    val resourceType: String,
    @SerializedName("root_id")
    val rootId: String,
    @SerializedName("user")
    val user: User
) {

    data class CountSummary(
        @SerializedName("favorite")
        val favorite: Favorite,
        @SerializedName("reply")
        val reply: Reply
    ) {
        data class Favorite(
            @SerializedName("count")
            val count: Int
        )

        data class Reply(
            @SerializedName("count")
            val count: Int
        )
    }

}