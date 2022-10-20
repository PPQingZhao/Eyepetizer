package com.pp.module_video_details

import com.pp.module_video_details.databinding.FragmentCommentsBinding
import com.pp.mvvm.LifecycleFragment

class CommentsFragment :
    LifecycleFragment<FragmentCommentsBinding, CommentsViewModel>() {
    override val mBinding by lazy { FragmentCommentsBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<CommentsViewModel> {
        return CommentsViewModel::class.java
    }

}