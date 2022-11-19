package com.pp.library_common.model

import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.SlideCoverViewModel

class MetroSlideCoverItemViewModel(m: Metro?): SlideCoverViewModel() {

    var metro: Metro? = null
        set(value) {
            field = value
            value?.metroData?.let {
                cover.set(it.cover.url)
            }
        }

    init {
        metro = m
    }
}