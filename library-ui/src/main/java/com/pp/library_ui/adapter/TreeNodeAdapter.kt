package com.pp.library_ui.adapter


open class TreeNodeAdapter : MultiBindingAdapter<TreeNode>() {

    override fun setDataList(list: List<TreeNode>) {
        val nodes = mutableListOf<TreeNode>()
        list.forEach {
            nodes.addAll(expandNode(it))
        }

//        Log.e("TAG","size: ${nodes.size}")
        super.setDataList(nodes)
    }

    override fun addDatas(list: List<TreeNode>) {
        val nodes = mutableListOf<TreeNode>()
        list.forEach {
            nodes.addAll(expandNode(it))
        }

        super.addDatas(nodes)
    }

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