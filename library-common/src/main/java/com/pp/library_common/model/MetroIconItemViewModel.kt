package com.pp.library_common.model

import com.pp.library_network.eyepetizer.bean.IconBean
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

    }
}