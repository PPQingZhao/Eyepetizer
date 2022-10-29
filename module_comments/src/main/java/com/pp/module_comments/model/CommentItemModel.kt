package com.pp.module_comments.model

import androidx.databinding.ObservableField
import com.pp.library_ui.adapter.TreeNode

open class CommentItemModel(parent: TreeNode? = null, expand: Boolean = true) :
    TreeNode(parent, expand) {
    val icon = ObservableField<String?>()
    val nick = ObservableField<String?>()
    val favorite = ObservableField<String?>()
    val comment = ObservableField<String?>()
}