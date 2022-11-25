package com.pp.library_common.model

import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.IconBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.model.IconItemViewModel

class MetroIconItemViewModel(iconBean: IconBean?): IconItemViewModel() {

    var icon: IconBean? = null
        set(value) {
            field = value

            value?.let {
                iconUrl.set(it.icon)
                name.set(it.name)
            }
        }
    init {
        icon = iconBean
    }

    override fun onIcon() {
        icon?.apply {
            val links = link.split("/?").getOrNull(0)
            val id = links?.split("/")?.last()

            ARouter.getInstance().build(RouterPath.Discovery.activity_tag_detail)
                .withString("id", id)
                .navigation()
        }
    }
}