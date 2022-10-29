package com.pp.module_comments.ui

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.adapter.TreeNodeAdapter
import com.pp.module_comments.databinding.FragmentCommentsBinding
import com.pp.module_comments.databinding.ItemCommentBinding
import com.pp.module_comments.databinding.ItemReplyBinding
import com.pp.module_comments.model.CommentItemViewModel
import com.pp.module_comments.model.ReplyItemViewModel
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.launch

@Route(path = RouterPath.Comments.fragment_comments)
class CommentsFragment :
    LifecycleFragment<FragmentCommentsBinding, CommentsViewModel>() {
    override val mBinding by lazy { FragmentCommentsBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<CommentsViewModel> {
        return CommentsViewModel::class.java
    }

    @JvmField
    @Autowired(name = "resourceId")
    var resourceId: Int? = 0

    @JvmField
    @Autowired(name = "resourceType")
    var resourceType: String? = ""

    override fun getModelFactory(): ViewModelProvider.Factory {
        return super.getModelFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ARouter.getInstance().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    val treeAdapter by lazy {
        val adapter = object : TreeNodeAdapter() {
//            override fun getItemId(position: Int): Long {
//                val item = getItem(position)
//                return when (item) {
//                    is CommentItemViewModel -> item.commentId()
//                    is ReplyItemViewModel -> item.commentId()
//                    else -> super.getItemId(position)
//                }
//            }
        }

//        adapter.setHasStableIds(true)

        val item_type_comment = 0
        val item_type_reply = item_type_comment + 1
        // comment
        adapter.addBindingItem(
            DefaultViewBindingItem<CommentItemViewModel>(
                item_type_comment,
                { true },
                { parent -> ItemCommentBinding.inflate(layoutInflater, parent, false) },
                { binding, item -> item })
        )

        // reply
        adapter.addBindingItem(
            DefaultViewBindingItem<ReplyItemViewModel>(
                item_type_reply,
                { true },
                { parent -> ItemReplyBinding.inflate(layoutInflater, parent, false) },
                { binding, item -> item })
        )

        adapter
    }

    val layoutManager = LinearLayoutManager(context)
    private fun initRecyclerView() {
        // hot
        mBinding.commentsHotRecyclerview.layoutManager = layoutManager
        mBinding.commentsHotRecyclerview.adapter = treeAdapter
        // time
//        mBinding.commentsTimeRecyclerview.layoutManager = layoutManager

    }

    override fun onFirstResume() {
        // 评论排序发生改变,重新加载评论数据
        mViewModel.sort_type.observe(this) { type ->
            lifecycleScope.launch {
                mViewModel.getComments(resourceId, resourceType, type).let {
                    if (it.code != EyepetizerService2.ErrorCode.SUCCESS) {
                        return@launch
                    }

                    val dataList = mutableListOf<CommentItemViewModel>()
                    it.result.itemList.forEach {
                        dataList.add(CommentItemViewModel(it))
                    }
/*
                    val nodes = mutableListOf<TreeNode>()
                    dataList.forEach {
                        nodes.addAll(TreeNodeAdapter.expandNode(it))
                    }

                    val nodeCount = nodes.size

                    val addData = nodes.size > treeAdapter.getDataList().size

                    val tempNodes = mutableListOf<TreeNode>()
                    tempNodes.addAll(nodes)
                    val adapterDataList = mutableListOf<TreeNode?>()
                    adapterDataList.addAll(treeAdapter.getDataList())
                    for ((index, adapterNode) in adapterDataList.withIndex()) {
                        if (index > tempNodes.size - 1) {
                            break
                        }

                        val newNode = tempNodes[index]
                        // 更新或者替换 adapter中的数据
                        nodes.remove(newNode)
                        // 更新
                        if (adapterNode is CommentItemViewModel && newNode is CommentItemViewModel) {
                            adapterNode.commentItem = newNode.commentItem
                            // 更新
                        } else if (adapterNode is ReplyItemViewModel && newNode is ReplyItemViewModel) {
                            adapterNode.replyItem = newNode.replyItem
                            // 替换
                        } else {
                            treeAdapter.removeData(index)
                            treeAdapter.addData(index, newNode)

                        }
                    }

                    if (addData) {
                        treeAdapter.addDatas(nodes)
                    } else {
                        val removeDatas = mutableListOf<TreeNode?>()
                        for (index in nodeCount..treeAdapter.getDataList().size - 1) {
                            removeDatas.add(treeAdapter.getItem(index))
                        }
                        treeAdapter.removeDataList(removeDatas)
                    }

                    layoutManager.scrollToPositionWithOffset(0, 0)*/


                    treeAdapter.setDataList(dataList)
/*
                    if (type == EyepetizerService2.SORT_TYPE_HOT) {

                        mBinding.commentsHotRecyclerview.adapter = treeAdapter
                        mBinding.commentsHotRecyclerview.visibility = View.VISIBLE

                        mBinding.commentsTimeRecyclerview.visibility = View.GONE
                    } else {

                        mBinding.commentsTimeRecyclerview.adapter = treeAdapter
                        mBinding.commentsTimeRecyclerview.visibility = View.VISIBLE

                        mBinding.commentsHotRecyclerview.visibility = View.GONE
                    }*/
                }
            }
        }
    }
}