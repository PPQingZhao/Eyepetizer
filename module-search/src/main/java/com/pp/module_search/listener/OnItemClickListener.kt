package com.pp.module_search.listener

import com.pp.module_search.model.SearchItemModel

interface OnItemClickListener {

    fun onDelete()

    fun onSelect(itemModel: SearchItemModel)
}