package com.pp.module_community.model

import com.pp.library_common.model.MetroBannerItemViewModel
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.module_community.respository.SquareType

class SquareBannerListViewModel(
    card: PageDataBean.Card,
    metroList: List<PageDataBean.Card.CardData.Body.Metro>
) : MetroBannerItemViewModel(card, metroList), MultiItemEntity {
    override val itemType: Int
        get() = SquareType.TYPE_BANNER_LIST

}