package com.pp.library_common.model

import com.pp.library_common.extension.intentToTopic
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.SlideCoverViewModel

class MetroSlideCoverTitleItemViewModel(m: Metro?) : SlideCoverViewModel() {

    var metro: Metro? = null
        set(value) {
            field = value
            value?.metroData?.let {
                cover.set(it.cover.url)
                title.set(it.title)
            }
        }

    init {
        metro = m
    }

    override fun onItem() {
        super.onItem()
        metro?.apply {
            val url = link.replace("eyepetizer:/", "")

            val links = link.split("?").getOrNull(0)
            val id = links?.split("/")?.last()

            intentToTopic(id)
        }
    }
}