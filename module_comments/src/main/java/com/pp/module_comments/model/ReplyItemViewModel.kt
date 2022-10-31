package com.pp.module_comments.model

import androidx.annotation.ColorInt
import com.pp.library_network.eyepetizer.bean.CommentsBean
import com.pp.module_comments.util.CommentUtil

class ReplyItemViewModel(reply: CommentsBean.Item.Reply, @ColorInt val color: Int) :
    CommentItemModel() {

    var replyItem: CommentsBean.Item.Reply? = null
        set(value) {
            field = value
            replyItem?.run {
                this@ReplyItemViewModel.icon.set(user.avatar)
                this@ReplyItemViewModel.nick.set(user.nick)
                this@ReplyItemViewModel.favorite.set(countSummary.favorite.count.toString())
                val commentResult: CharSequence
                if (atUser.uid > 0) {
                    // 回复类型
                    commentResult =
                        CommentUtil.getReply(
                            atUser.nick,
                            commentContent,
                            commentTime,
                            location,
                            0.6f,
                            color
                        )
                } else {
                    commentResult =
                        CommentUtil.getComment(commentContent, commentTime, location, 0.6f, color)
                }
                this@ReplyItemViewModel.comment.set(commentResult)

            }
        }

    init {
        this.replyItem = reply
    }

}