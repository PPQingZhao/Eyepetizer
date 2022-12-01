package com.pp.module_comments.model

import androidx.annotation.ColorInt
import com.pp.library_network.eyepetizer.bean.Comment
import com.pp.module_comments.util.CommentUtil

class ReplyItemViewModel(reply: Comment, @ColorInt val color: Int) :
    CommentItemModel() {

    var replyItem: Comment? = null
        set(value) {
            field = value
            this@ReplyItemViewModel.icon.set(value?.user?.avatar)
            this@ReplyItemViewModel.nick.set(value?.user?.nick)
            this@ReplyItemViewModel.favorite.set(value?.countSummary?.favorite?.count.toString())
            val commentResult: CharSequence
            if ((value?.atUser?.uid ?: 0) > 0) {
                // 回复类型
                commentResult =
                    CommentUtil.getReply(
                        value?.atUser?.nick,
                        value?.commentContent,
                        value?.commentTime,
                        value?.location,
                        0.6f,
                        color
                    )
            } else {
                commentResult =
                    CommentUtil.getComment(
                        value?.commentContent,
                        value?.commentTime,
                        value?.location,
                        0.6f,
                        color
                    )
            }
            this@ReplyItemViewModel.comment.set(commentResult)

        }

    init {
        this.replyItem = reply
    }

}