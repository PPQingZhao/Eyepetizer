package com.pp.module_comments.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.adapter.*
import com.pp.library_base.base.ThemeFragment
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.R
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.adapter.TreeNode
import com.pp.library_ui.utils.DividerDecoration
import com.pp.library_ui.utils.StateView
import com.pp.module_comments.databinding.FragmentCommentsBinding
import com.pp.module_comments.databinding.ItemCommentBindingImpl
import com.pp.module_comments.databinding.ItemReplyBindingImpl
import com.pp.module_comments.model.CommentItemViewModel
import com.pp.module_comments.model.ReplyItemViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = RouterPath.Comments.fragment_comments)
class CommentsFragment :
    ThemeFragment<FragmentCommentsBinding, CommentsViewModel>() {
    override val mBinding by lazy { FragmentCommentsBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<CommentsViewModel> {
        return CommentsViewModel::class.java
    }

    @JvmField
    @Autowired(name = "resourceId")
    var resourceId: Long? = 0

    @JvmField
    @Autowired(name = "resourceType")
    var resourceType: String? = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ARouter.getInstance().inject(this)
    }

    private val treeAdapter by lazy {
        val adapter =
            TreeNodeAdapter(object : DiffUtil.ItemCallback<TreeNode>() {
                override fun areItemsTheSame(oldItem: TreeNode, newItem: TreeNode): Boolean {
                    val result =
                        if (oldItem is CommentItemViewModel && newItem is CommentItemViewModel) {
                            oldItem.commentItem?.commentId == newItem.commentItem?.commentId
                        } else if (oldItem is ReplyItemViewModel && newItem is ReplyItemViewModel) {
                            oldItem.replyItem?.commentId == newItem.replyItem?.commentId
                        } else {
                            oldItem == newItem
                        }
//                    Log.e("TAG", "${result}")
                    return result
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: TreeNode, newItem: TreeNode): Boolean {
                    val result =
                        if (oldItem is CommentItemViewModel && newItem is CommentItemViewModel) {
                            oldItem.commentItem?.commentId == newItem.commentItem?.commentId
                        } else if (oldItem is ReplyItemViewModel && newItem is ReplyItemViewModel) {
                            oldItem.replyItem?.commentId == newItem.replyItem?.commentId
                        } else {
                            oldItem == newItem
                        }
//                    Log.e("TAG", "content: ${result}")
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

        mBinding.commentsHotRecyclerview.layoutManager = linearLayoutManager
        mBinding.commentsHotRecyclerview.addItemDecoration(
            DividerDecoration(
                1,
                resources.getColor(R.color.gray),
                RecyclerView.VERTICAL
            ) { position, type ->
                if (position == null) {
                    return@DividerDecoration false
                }

                // ?????? ????????????
                if (position >= treeAdapter.itemCount) {
                    return@DividerDecoration false
                }

                // ????????? bottom divider,????????????
                if (type != DividerDecoration.DIVIDER_BOTTOM) {
                    return@DividerDecoration false
                }

                // ?????? comment item bottom divider
                val itemData = treeAdapter.getItemData(position)
                when (itemData) {
                    is CommentItemViewModel -> {
                        itemData.getChildNodes().isEmpty()
                    }

                    is ReplyItemViewModel -> {
                        itemData.parent?.run {
                            getNode(getChildNodes().size - 1) === itemData
                        } ?: false
                    }
                    else -> {
                        false
                    }
                }
            }
        )
        mBinding.commentsHotRecyclerview.adapter =
            treeAdapter.withLoadStateFooter(
                DefaultLoadMoreStateAdapter(
                    lifecycle = lifecycle,
                    treeAdapter.onErrorListener()
                )
            )
    }

    override fun onFirstResume() {
        // todo:???????????? ?????????bug??????????????????????????????recyclerview onBindViewHolder
        mBinding.commentRefresh.isEnabled = false
        initRecyclerView()

        lifecycleScope.launch{
            treeAdapter.attachRefreshView(mBinding.commentRefresh)
        }

        lifecycleScope.launch{

            treeAdapter.attachStateView(
                StateView.DefaultBuilder(
                    viewLifecycleOwner.lifecycle,
                    mBinding.commentsHotRecyclerview
                )
                    .setOnErrorClickListener(treeAdapter.onErrorListener())
                    .setThemeViewModel(mBinding.themeViewModel)
                    .build()
            )
        }

        lifecycleScope.launch {

            mViewModel.getPageData(
                resourceId,
                resourceType,
                EyepetizerService2.SortType.SORT_TYPE_HOT,
                requireTheme().textColorSecondary.value ?: Color.GRAY
            ).collect {
                treeAdapter.submitData(it)
            }
        }

        // ????????????????????????,????????????????????????
        mViewModel.sort_type.observe(this) { type ->
            linearLayoutManager.scrollToPositionWithOffset(0, 0)
            treeAdapter.refresh()
        }
    }

}
