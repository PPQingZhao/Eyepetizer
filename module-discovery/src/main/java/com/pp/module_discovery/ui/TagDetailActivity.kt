package com.pp.module_discovery.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_router_service.services.RouterPath
import com.pp.module_discovery.databinding.ActivityTagDetailBinding
import com.pp.mvvm.LifecycleActivity

@Route(path = RouterPath.Discovery.activity_tag_detail)
class TagDetailActivity : LifecycleActivity<ActivityTagDetailBinding, TagDetailViewModel>() {

    @JvmField
    @Autowired(name = "id")
    var id: String = ""

    override val mBinding by lazy { ActivityTagDetailBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<TagDetailViewModel> {
        return TagDetailViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        mViewModel.getDetail(id ?: "")
    }
}