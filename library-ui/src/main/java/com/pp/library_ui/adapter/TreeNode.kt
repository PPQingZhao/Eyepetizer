package com.pp.library_ui.adapter

open class TreeNode(var parent: TreeNode? = null, var expand: Boolean = true) {

    /**
     * 子节点集合
     */
    private val childNodes by lazy { mutableListOf<TreeNode>() }

    fun getChildNodes(): Collection<TreeNode> {
        return childNodes
    }

    fun getNode(@androidx.annotation.IntRange(from = 0) index: Int): TreeNode {
        return childNodes[index]
    }

    /**
     * 添加节点
     */
    fun addNode(node: TreeNode) {
        // 节点存在 parent
        if (node.parent != null) throw IllegalArgumentException(
            "The specified node already has a parent. \" +\n" +
                    "                    \"You must call removeNode() on the node's parent first."
        )

        node.parent = this
        childNodes.add(node)
    }

    /**
     * 删除节点
     */
    fun removeNode(node: TreeNode) {
        val indexOfNode = childNodes.indexOf(node)
        if (indexOfNode >= 0) {
            node.parent = null
            childNodes.remove(node)
        }
    }


}