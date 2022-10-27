package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class CommentsBean(
    @SerializedName("item_count")
    val itemCount: Int,
    @SerializedName("item_list")
    val itemList: List<Item>,
    @SerializedName("item_per_page")
    val itemPerPage: Int,
    @SerializedName("last_item_id")
    val lastItemId: Int,
    @SerializedName("page_count")
    val pageCount: Int
) {
    data class Item(
        @SerializedName("at_user")
        val atUser: AtUser,
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
        val replyList: List<Reply>,
        @SerializedName("resource_id")
        val resourceId: String,
        @SerializedName("resource_type")
        val resourceType: String,
        @SerializedName("root_id")
        val rootId: String,
        @SerializedName("user")
        val user: User
    ) {
        class AtUser

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

        data class Reply(
            @SerializedName("at_user")
            val atUser: AtUser,
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
            val replyList: List<Any>,
            @SerializedName("resource_id")
            val resourceId: String,
            @SerializedName("resource_type")
            val resourceType: String,
            @SerializedName("root_id")
            val rootId: String,
            @SerializedName("user")
            val user: User
        ) {
            data class AtUser(
                @SerializedName("avatar")
                val avatar: String,
                @SerializedName("description")
                val description: String,
                @SerializedName("is_mine")
                val isMine: Boolean,
                @SerializedName("link")
                val link: String,
                @SerializedName("nick")
                val nick: String,
                @SerializedName("uid")
                val uid: Int,
                @SerializedName("user_type")
                val userType: String
            )

            data class CountSummary(
                @SerializedName("favorite")
                val favorite: Favorite
            ) {
                data class Favorite(
                    @SerializedName("count")
                    val count: Int
                )
            }

            data class User(
                @SerializedName("avatar")
                val avatar: String,
                @SerializedName("description")
                val description: String,
                @SerializedName("is_mine")
                val isMine: Boolean,
                @SerializedName("link")
                val link: String,
                @SerializedName("nick")
                val nick: String,
                @SerializedName("uid")
                val uid: Int,
                @SerializedName("user_type")
                val userType: String
            )
        }

        data class User(
            @SerializedName("avatar")
            val avatar: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("is_mine")
            val isMine: Boolean,
            @SerializedName("link")
            val link: String,
            @SerializedName("nick")
            val nick: String,
            @SerializedName("uid")
            val uid: Int,
            @SerializedName("user_type")
            val userType: String
        )
    }
}
