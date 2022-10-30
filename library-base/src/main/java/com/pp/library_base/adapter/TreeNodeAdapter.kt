package com.pp.library_base.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pp.library_ui.adapter.TreeNode


open class TreeNodeAdapter(diffCallback: DiffUtil.ItemCallback<TreeNode>) : MultiBindingPagingDataAdapter<TreeNode>(diffCallback) {

    companion object {

        fun expandNode(it: TreeNode): Collection<TreeNode> {
            val nodes = mutableListOf<TreeNode>()
            nodes.add(it)
            if (it.expand) {
                it.getChildNodes().forEach { it ->
                    nodes.addAll(expandNode(it))
                }
            }
            return nodes
        }
    }


}