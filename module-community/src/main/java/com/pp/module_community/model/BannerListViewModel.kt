package com.pp.module_community.model

import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.module_community.respository.SquareType

class BannerListViewModel(
    val card: PageDataBean.Card,
    val metroList: List<PageDataBean.Card.CardData.Body.Metro>
): MultiItemEntity {



    override val itemType: Int
        get() = SquareType.TYPE_BANNER_LIST
}