package com.pp.module_comments.model

import androidx.annotation.ColorInt
import com.pp.library_network.eyepetizer.bean.Comment
import com.pp.module_comments.util.CommentUtil

class CommentItemViewModel(comment: Comment, @ColorInt val color: Int) :
    CommentItemModel(expand = true) {


    var commentItem: Comment? = null
        set(value) {
            field = value
            value?.replyList?.forEach {
                val replyItemViewModel = ReplyItemViewModel(it, color)
                addNode(replyItemViewModel)
            }
            this@CommentItemViewModel.icon.set(value?.user?.avatar)
            this@CommentItemViewModel.nick.set(value?.user?.nick)
            this@CommentItemViewModel.favorite.set(value?.countSummary?.favorite?.count.toString())
            val commentResult =
                CommentUtil.getComment(
                    value?.commentContent,
                    value?.commentTime,
                    value?.location,
                    0.6f,
                    color
                )
            this@CommentItemViewModel.comment.set(commentResult)
        }

    init {
        this.commentItem = comment
    }


}