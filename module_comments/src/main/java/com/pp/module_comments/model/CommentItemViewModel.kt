package com.pp.module_comments.model

import androidx.annotation.ColorInt
import com.pp.library_network.eyepetizer.bean.CommentsBean
import com.pp.module_comments.util.CommentUtil

class CommentItemViewModel(comment: CommentsBean.Item, @ColorInt val color: Int) :
    CommentItemModel(expand = true) {


    var commentItem: CommentsBean.Item? = null
        set(value) {
            field = value
            value?.run {
                replyList.forEach {
                    val replyItemViewModel = ReplyItemViewModel(it, color)
                    addNode(replyItemViewModel)
                }
                this@CommentItemViewModel.icon.set(user.avatar)
                this@CommentItemViewModel.nick.set(user.nick)
                this@CommentItemViewModel.favorite.set(countSummary.favorite.count.toString())
                val commentResult =
                    CommentUtil.getComment(commentContent, commentTime, location, 0.6f, color)
                this@CommentItemViewModel.comment.set(commentResult)
            }
        }

    init {
        this.commentItem = comment
    }

}