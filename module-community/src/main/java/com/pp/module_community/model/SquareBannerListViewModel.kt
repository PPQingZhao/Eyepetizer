package com.pp.module_community.model

import com.pp.library_common.model.MetroBannerItemViewModel
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.module_community.respository.SquareType

class SquareBannerListViewModel(
    card: Card,
    metroList: List<Metro>
) : MetroBannerItemViewModel(card, metroList), MultiItemEntity {
    override val itemType: Int
        get() = SquareType.TYPE_BANNER_LIST

}