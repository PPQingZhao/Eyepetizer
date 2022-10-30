package com.pp.module_comments.model

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_network.eyepetizer.bean.CommentsBean

class CommentItemViewModel(comment: CommentsBean.Item) : CommentItemModel(expand = true) {


    var commentItem: CommentsBean.Item? = null
        set(value) {
            Log.e("TAG", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            field = value
            value?.run {
                replyList.forEach {
                    val replyItemViewModel = ReplyItemViewModel(it)
                    addNode(replyItemViewModel)
                }
                /*  this@CommentItemViewModel.icon.set(user.avatar)
                  this@CommentItemViewModel.nick.set(user.nick)
                  this@CommentItemViewModel.favorite.set(countSummary.favorite.count.toString())
                  this@CommentItemViewModel.comment.set(commentContent)*/

                this@CommentItemViewModel.icon = user.avatar
                this@CommentItemViewModel.nick = user.nick
                this@CommentItemViewModel.favorite = (countSummary.favorite.count.toString())
                this@CommentItemViewModel.comment = (commentContent)
            }
        }

    init {
        this.commentItem = comment
    }


    fun commentId(): Long {
        return try {
            val id = commentItem?.commentId?.split("-")?.get(0)?.toLong() ?: RecyclerView.NO_ID
            id
        } catch (e: Exception) {
            e.printStackTrace()
            RecyclerView.NO_ID
        }
    }
}