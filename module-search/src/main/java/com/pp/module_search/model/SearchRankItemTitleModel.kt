package com.pp.module_search.model

import androidx.databinding.ObservableField
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.PageDataBean

class SearchRankItemTitleModel(val text: String, override val itemType: Int): MultiItemEntity {
    val title = ObservableField<String>()

    init {
        title.set(text)
    }

    fun onClickItem() {

    }

}