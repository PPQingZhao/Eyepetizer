package com.pp.library_common.model

import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_ui.model.SlideCoverViewModel

class MetroItemSlideCoverViewModel(m: MetroDataBean.Item?): SlideCoverViewModel() {

    var metroItem: MetroDataBean.Item? = null
        set(value) {
            field = value
            value?.let {
                cover.set(it.cover.url)
                title.set(it.title)
            }
        }

    init {
        metroItem = m
    }
}