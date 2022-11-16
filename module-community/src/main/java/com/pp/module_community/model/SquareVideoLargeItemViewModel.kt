package com.pp.module_community.model

import com.pp.library_common.model.MetroFollowItemViewModel
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_LARGE

class SquareVideoLargeItemViewModel(val m: PageDataBean.Card.CardData.Body.Metro?) :
    MetroFollowItemViewModel(m), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_LARGE
}