package com.pp.library_common.model

import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.WebViewModel

class MetroWebViewItemViewModel(m: Metro?): WebViewModel() {

    var metro: Metro? = null
        set(value) {
            field = value
            value?.metroData?.let {
                url.set(it.url)
            }
        }

    init {
        metro = m
    }
}