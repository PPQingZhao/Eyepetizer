package com.pp.module_search.model

import com.pp.library_common.model.ItemModel
import com.pp.module_search.listener.OnItemClickListener

class SearchItemModel(type: String, data: String, val itemClickListener: OnItemClickListener? = null): ItemModel<String>(type, data) {

    fun onDelete() {
        itemClickListener?.onDelete()
    }

    fun onSelect(itemModel: SearchItemModel) {
        itemClickListener?.onSelect(itemModel)
    }
}