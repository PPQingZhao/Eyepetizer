package com.pp.module_community.model

import com.pp.library_common.model.MetroFollowItemViewModel2
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_LARGE

class SquareVideoLargeItemViewModel(val m: Metro?) :
    MetroFollowItemViewModel2(m), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_LARGE
}