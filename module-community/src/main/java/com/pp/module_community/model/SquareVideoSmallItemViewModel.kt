package com.pp.module_community.model

import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.model.VideoSmallCardItemViewModel
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_SMALL

class SquareVideoSmallItemViewModel(val metro: PageDataBean.Card.CardData.Body.Metro?) :
    VideoSmallCardItemViewModel(metro), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_SMALL
}