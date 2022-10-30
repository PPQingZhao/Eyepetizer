package com.pp.module_comments.model

import com.pp.library_ui.adapter.TreeNode

open class CommentItemModel(parent: TreeNode? = null, expand: Boolean = false) :
    TreeNode(parent, expand) {
    /*  val icon = ObservableField<String>()
      val nick = ObservableField<String>()
      val favorite = ObservableField<String>()
      val comment = ObservableField<String>()*/


    var icon = ""
    var nick = ""
    var favorite = ""
    var comment = ""


}