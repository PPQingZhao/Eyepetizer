package com.pp.module_comments.util

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

object CommentUtil {
    /**
     * 构建评论内容
     */
    fun getComment(
        comment: String?,
        commentTime: String?,
        location: String?,
        @FloatRange(from = 0.0) proportion: Float,
        @ColorInt color: Int
    ): CharSequence {
        val locationContent = if (location?.length == 0) "" else " ${location}"
        val commentSpannable = SpannableStringBuilder(comment)
            .append("  ${commentTime}${locationContent}")
        // 字体相对大小
        val relativeSizeSpan = RelativeSizeSpan(proportion)
        // 字体颜色
        val foregroundColorSpan = ForegroundColorSpan(color)

        val start = comment?.length?:0
        val end = commentSpannable.length
        commentSpannable.setSpan(relativeSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        commentSpannable.setSpan(
            foregroundColorSpan,
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return commentSpannable
    }

    /**
     * 构建回复内容
     */
    fun getReply(
        at_user: String?,
        comment: String?,
        location: String?,
        commentTime: String?,
        @FloatRange(from = 0.0) proportion: Float,
        @ColorInt color: Int
    ): CharSequence {
        // 构建回复对象
        val replyText = "回复 "
        val replySpannable = SpannableStringBuilder(replyText)
            .append(at_user)
        // 字体颜色
        val foregroundColorSpan = ForegroundColorSpan(color)
        val start = replyText.length
        val end = replySpannable.length
        replySpannable.setSpan(
            foregroundColorSpan,
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        // 评论内容
        val commentResult = getComment(comment, commentTime, location, proportion, color)

        replySpannable
            .append(" ")
            .append(commentResult)
        return replySpannable
    }
}