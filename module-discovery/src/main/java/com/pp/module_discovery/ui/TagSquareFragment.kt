package com.pp.module_discovery.ui

import com.pp.library_base.base.ThemeFragment
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.module_discovery.databinding.FragmentTagSquareBinding

class TagSquareFragment(tab: TagDetailBean.TabInfo.Tab?) : ThemeFragment<FragmentTagSquareBinding, TagSquareViewModel>() {
    override val mBinding by lazy {
        FragmentTagSquareBinding.inflate(layoutInflater)
    }
    override fun getModelClazz(): Class<TagSquareViewModel> {
        return TagSquareViewModel::class.java
    }

    private var url = ""

    init {
        url = tab?.apiUrl ?: ""
    }

}