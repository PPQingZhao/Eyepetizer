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
import com.pp.library_ui.R
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.adapter.TreeNodeAdapter
import com.pp.module_comments.databinding.FragmentCommentsBinding
import com.pp.module_comments.databinding.ItemCommentBindingImpl
import com.pp.module_comments.databinding.ItemReplyBindingImpl
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

    private val treeAdapter by lazy {
        val adapter = TreeNodeAdapter()

        val item_type_comment = 0
        val item_type_reply = item_type_comment + 1
        // comment
        adapter.addBindingItem(
            DefaultViewBindingItem<CommentItemViewModel>(
                item_type_comment,
                { it != null },
                { parent -> ItemCommentBindingImpl.inflate(layoutInflater, parent, false) },
                { binding, item, cacheItemViewModel ->
                    if (cacheItemViewModel is CommentItemViewModel) cacheItemViewModel else item
                })
        )

        // reply
        adapter.addBindingItem(
            DefaultViewBindingItem<ReplyItemViewModel>(
                item_type_reply,
                { it != null },
                { parent -> ItemReplyBindingImpl.inflate(layoutInflater, parent, false) },
                { binding, item, cacheItemViewModel ->
                    if (cacheItemViewModel is ReplyItemViewModel) cacheItemViewModel else item
                })
        )

        adapter
    }

    val linearLayoutManager = LinearLayoutManager(context)
    private fun initRecyclerView() {
        // hot
        mBinding.commentsHotRecyclerview.layoutManager = linearLayoutManager
        mBinding.commentsHotRecyclerview.adapter = treeAdapter

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
                        dataList.add(
                            CommentItemViewModel(
                                it,
                                resources.getColor(R.color.mediaTextColorSecondary)
                            )
                        )
                    }

                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    treeAdapter.setDataList(dataList)

                }
            }
        }
    }
}