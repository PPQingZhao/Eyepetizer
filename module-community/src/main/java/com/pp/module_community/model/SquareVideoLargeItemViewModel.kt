package com.pp.module_community.model

import com.pp.library_common.model.MetroFollowItemViewModel
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_LARGE

class SquareVideoLargeItemViewModel(m: Metro?) :
    MetroFollowItemViewModel(m, false), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_LARGE
}