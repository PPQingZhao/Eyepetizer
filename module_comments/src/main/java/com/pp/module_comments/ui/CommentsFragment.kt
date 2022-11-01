package com.pp.module_comments.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.adapter.CustomLoadMoreStateAdapter
import com.pp.library_base.adapter.TreeNodeAdapter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.R
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.adapter.TreeNode
import com.pp.module_comments.databinding.FragmentCommentsBinding
import com.pp.module_comments.databinding.ItemCommentBindingImpl
import com.pp.module_comments.databinding.ItemReplyBindingImpl
import com.pp.module_comments.model.CommentItemViewModel
import com.pp.module_comments.model.ReplyItemViewModel
import com.pp.mvvm.LifecycleFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ARouter.getInstance().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initRefreshLayout()
    }

    private fun initRefreshLayout() {
        mBinding.commentRefresh.setColorSchemeResources(R.color.colorAccent)
        mBinding.commentRefresh.setOnRefreshListener {
//            stateAdapter.loadState =LoadState.Loading
            treeAdapter.refresh()
        }
    }

    private val treeAdapter by lazy {
        val adapter = TreeNodeAdapter(object : DiffUtil.ItemCallback<TreeNode>() {
            override fun areItemsTheSame(oldItem: TreeNode, newItem: TreeNode): Boolean {
                val result =
                    if (oldItem is CommentItemViewModel && newItem is CommentItemViewModel) {
                        oldItem.commentItem?.commentId == newItem.commentItem?.commentId
                    } else if (oldItem is ReplyItemViewModel && newItem is ReplyItemViewModel) {
                        oldItem.replyItem?.commentId == newItem.replyItem?.commentId
                    } else {
                        oldItem == newItem
                    }
//                Log.e("TAG", "${result}")
                return result
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TreeNode, newItem: TreeNode): Boolean {
                val result =
                    if (oldItem is CommentItemViewModel && newItem is CommentItemViewModel) {
//                        Log.e("TAG", "comment oldItem: ${oldItem} newItem: ${newItem} ")
                        oldItem.commentItem?.commentId == newItem.commentItem?.commentId
                    } else if (oldItem is ReplyItemViewModel && newItem is ReplyItemViewModel) {
//                        Log.e("TAG", "reply oldItem: ${oldItem} newItem: ${newItem} ")
                        oldItem.replyItem?.commentId == newItem.replyItem?.commentId
                    } else {
                        oldItem == newItem
                    }
//                Log.e("TAG", "content: ${result}")
                return result
            }
        })


        val item_type_comment = 0
        val item_type_reply = item_type_comment + 1
        // comment
        adapter.addBindingItem(
            DefaultViewBindingItem<CommentItemViewModel>(
                item_type_comment,
                { it != null },
                { parent -> ItemCommentBindingImpl.inflate(layoutInflater, parent, false) },
                { binding, item, cacheItemViewModel -> item })
        )

        // reply
        adapter.addBindingItem(
            DefaultViewBindingItem<ReplyItemViewModel>(
                item_type_reply,
                { it != null },
                { parent -> ItemReplyBindingImpl.inflate(layoutInflater, parent, false) },
                { binding, item, cacheItemViewModel -> item })
        )

        adapter
    }

    private val linearLayoutManager = LinearLayoutManager(context)
    private fun initRecyclerView() {

        // hot
        mBinding.commentsHotRecyclerview.layoutManager = linearLayoutManager
        mBinding.commentsHotRecyclerview.adapter =
            treeAdapter.withLoadStateFooter(CustomLoadMoreStateAdapter(R.color.mediaTextColor) {
                treeAdapter.retry()
            })
    }

    override fun onFirstResume() {
        lifecycleScope.launch {
            mViewModel.getPageData(resourceId, resourceType, EyepetizerService2.SORT_TYPE_HOT)
                .collect {
                    treeAdapter.submitData(it)
                }
        }

        lifecycleScope.launch {
            treeAdapter.loadStateFlow.collectLatest {
                mBinding.commentRefresh.isRefreshing = it.refresh is LoadState.Loading
            }

        }

        // 评论排序发生改变,重新加载评论数据
        mViewModel.sort_type.observe(this) { type ->
            linearLayoutManager.scrollToPositionWithOffset(0, 0)
            treeAdapter.refresh()
        }
    }
}
