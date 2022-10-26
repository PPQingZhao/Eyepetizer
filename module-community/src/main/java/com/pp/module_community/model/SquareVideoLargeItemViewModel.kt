package com.pp.module_community.model

import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.model.VideoCardItemViewModel
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_LARGE

class SquareVideoLargeItemViewModel(val metro: PageDataBean.Card.CardData.Body.Metro?) :
    VideoCardItemViewModel(metro), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_LARGE
}