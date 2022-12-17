package com.pp.module_community.model

import com.pp.library_common.model.MetroImageFollowItemViewModel
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.module_community.respository.SquareType.TYPE_IMAGE_LARGE
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_LARGE

class SquareImageLargeItemViewModel(m: Metro?) :
    MetroImageFollowItemViewModel(m, false), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_IMAGE_LARGE
}