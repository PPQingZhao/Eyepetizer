package com.pp.module_search.model

import androidx.databinding.ObservableField
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.Metro

class SearchRankItemModel(val metro: Metro,
                          override val itemType: Int
): MultiItemEntity {
    val img = ObservableField<String>()
    val title = ObservableField<String>()

    init {
        img.set(metro.metroData?.cover?.url)
        title.set(metro.metroData?.title)
    }

    fun onClickItem() {

    }
}