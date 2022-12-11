package com.pp.library_common.model

import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.LargeImageItemViewModel

open class MetroLargeImageCardItemViewModel(item: Metro?) : LargeImageItemViewModel() {
    protected var resourceId: Long? = null
    protected var resourceType: String? = null
    var metro: Metro? = null
        set(value) {
            field = value

            resourceId = value?.metroData?.resourceId
            resourceType = value?.metroData?.resourceType

            cover.set(value?.metroData?.cover?.url)
        }

    init {
        metro = item
    }
}