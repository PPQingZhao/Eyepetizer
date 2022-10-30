package com.pp.module_comments.model

import androidx.recyclerview.widget.RecyclerView
import com.pp.library_network.eyepetizer.bean.CommentsBean

class ReplyItemViewModel(reply: CommentsBean.Item.Reply) :  CommentItemModel() {

    var replyItem: CommentsBean.Item.Reply? = null
       /* set(value) {
            field = value
            replyItem?.run {
                this@ReplyItemViewModel.icon.set(user.avatar)
                this@ReplyItemViewModel.nick.set(user.nick)
                this@ReplyItemViewModel.favorite.set(countSummary.favorite.count.toString())
                this@ReplyItemViewModel.comment.set(commentContent)

                this@ReplyItemViewModel.icon.set("user.avatar")
                this@ReplyItemViewModel.nick.set("user.nick")
                this@ReplyItemViewModel.favorite.set(countSummary.favorite.count.toString())
                this@ReplyItemViewModel.comment.set("commentContent")
            }
        }*/

    init {
        this.replyItem = reply
    }

    fun commentId(): Long {
        return try {
            val id = replyItem?.commentId?.split("-")?.get(0)?.toLong() ?: RecyclerView.NO_ID
            id
        } catch (e: Exception) {
            e.printStackTrace()
            RecyclerView.NO_ID
        }
    }
}