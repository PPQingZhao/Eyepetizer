package com.pp.module_community.model

import android.util.Log
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.module_community.respository.SquareType.TYPE_VIDEO_SMALL

class SquareVideoSmallItemViewModel(val metro: PageDataBean.Card.CardData.Body.Metro?) :
    FollowVideoSmallItemViewModel(metro), MultiItemEntity {
    override val itemType: Int
        get() = TYPE_VIDEO_SMALL

    init {
        metro?.apply {
            imagePath.set(metroData.cover.url)
            description.set(metroData.title)
            metroData.author?.avatar?.url?.let {
                avatarPath.set(it)
            }

            nickname.set(metroData.author?.nick)
        }
        Log.e("TAG", "=== imagePath: ${imagePath.get()}")
        Log.e("TAG", "=== description: ${description.get()}")
    }

}