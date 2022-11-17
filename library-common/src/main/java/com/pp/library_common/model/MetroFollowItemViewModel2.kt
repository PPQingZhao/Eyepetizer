package com.pp.library_common.model

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.R
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel

open class MetroFollowItemViewModel2(
    item: Metro?,
) : FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    companion object {
        private const val TAG = "MetroFollowItem"
    }

    var resourceId: Long? = null
    var resourceType: String? = null

    var metro: Metro? = null
        set(value) {
            field = value

            val metroData = field?.metroData
            this.isVideo = metroData?.resourceType == EyepetizerService2.MetroType.ResourceType.pgc_video

            this.drawableFolow.set(
                if (metroData?.isMine
                        ?: false
                ) R.drawable.ic_more_vert_24 else R.drawable.layer_follow
            )

            resourceId = metroData?.resourceId
            resourceType = metroData?.resourceType

            this.icon.set(metroData?.author?.avatar?.url)
            this.author.set(metroData?.author?.nick)
            this.date.set(metroData?.rawPublishTime)
            this.area.set(metroData?.realLocation)
            this.content.set(metroData?.text)
            this.category.set(metroData?.category?.name)
            this.collectionCount
                .set(metroData?.consumption?.likeCount.toString())
            this.realCollectionCount
                .set(metroData?.consumption?.collectionCount.toString())
            this.replyCount
                .set(metroData?.consumption?.commentCount.toString())

            val coverList = mutableListOf<ImageVideoItemViewModel>()
            metroData?.images?.forEach { it ->
                coverList.add(ImageVideoItemViewModel(ObservableField(it?.cover?.url), false))
            }

            metroData?.video?.cover?.apply {
                coverList.add(ImageVideoItemViewModel(ObservableField(url), true))
            }

            mAdapter.setDataList(coverList)
        }

    private val mAdapter by lazy { Adapter() }

    init {
        adapter = mAdapter
        metro = item
    }

    inner class Adapter :
        BindingAdapter<ItemImageVideoBinding, ImageVideoItemViewModel, ImageVideoItemViewModel>() {
        override fun createViewModel(
            binding: ItemImageVideoBinding,
            item: ImageVideoItemViewModel?,
            cacheItemViewModel: ImageVideoItemViewModel?
        ): ImageVideoItemViewModel? {
            return cacheItemViewModel ?: item
        }

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemImageVideoBinding {
            return ItemImageVideoBinding.inflate(layoutInflater, parent, false)
        }

    }

    override fun onVideo(view: View) {
        ARouter.getInstance()
            .build(RouterPath.VideoDetails.activity_video_details)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }
}