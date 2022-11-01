package com.pp.module_comments.model

import androidx.databinding.ObservableField
import com.pp.library_ui.adapter.TreeNode

open class CommentItemModel(parent: TreeNode? = null, expand: Boolean = false) :
    TreeNode(parent, expand) {
    val icon = ObservableField<String>()
    val nick = ObservableField<String>()
    val favorite = ObservableField<String>()
    val comment = ObservableField<CharSequence>()


    override fun equals(other: Any?): Boolean {
        return if (this === other) {
            true
        } else if (other == null || !(other is CommentItemModel)) {
            false
        } else {
            this.icon.get() == other.icon.get()
                    && this.nick.get() == other.nick.get()
                    && this.favorite.get() == other.favorite.get()
                    && this.comment.get() == other.comment.get()
        }
    }

    override fun toString(): String {
        return """
            ----------------------------------------------
            nick:  ${nick.get()}
            comment: ${comment.get()}
            ---------------------------------------------
            """.trimIndent()
    }

}