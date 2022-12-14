package com.pp.library_common.model

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_common.extension.isVideo
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.R
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.library_ui.databinding.ItemImageBindingImpl
import com.pp.library_ui.databinding.ItemVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel

open class MetroFollowItemViewModel(
    item: Metro?,
    private val mine: Boolean = false,
) : FollowCardItemViewModel<ImageVideoItemViewModel>() {

    companion object {
        private const val TAG = "MetroFollowItem"
    }

    var resourceId: Long? = null
    var resourceType: String? = null

    var metro: Metro? = null
        set(value) {
            field = value

            val metroData = value?.metroData
            this.isVideo = metroData?.isVideo() == true

            this.drawableFollow.set(
                if (mine && metroData?.isMine == true)
                    R.drawable.ic_more_vert_24
                else if (metroData?.author?.followed == true || metroData?.isMine == true) R.drawable.layer_followed
                else R.drawable.layer_follow
            )

            resourceId = metroData?.resourceId
            resourceType = metroData?.resourceType

            this.icon.set(metroData?.author?.avatar?.url)
            this.author.set(metroData?.author?.nick)
            this.date.set(metroData?.rawPublishTime)
            this.area.set(metroData?.realLocation)
            this.content.set(metroData?.text)

            var topic = ""
            metroData?.topics?.forEach {
                topic = "${topic}${it.title} "
            }

            this.category.set(topic)
            this.collectionCount
                .set(metroData?.consumption?.likeCount.toString())
            this.realCollectionCount
                .set(metroData?.consumption?.collectionCount.toString())
            this.replyCount
                .set(metroData?.consumption?.commentCount.toString())

//            val coverList = mutableListOf<ImageVideoItemViewModel>()


            dataList.clear()
            if (isVideo) {
                metroData?.video?.apply {
                    val coverUrl = ObservableField(this.cover.url)
                    val playUrl = ObservableField(this.playUrl)
                    dataList.add(ImageVideoItemViewModel.VideoItemViewModel(coverUrl, playUrl))
                }
            } else {
                metroData?.images?.forEach { it ->
                    it?.cover?.apply {
                        dataList.add(ImageVideoItemViewModel.ImageItemViewModel(ObservableField(url)))
                    }
                }
            }

            indicatorCount.set(dataList.size)
//            mAdapter.setDataList(coverList)
        }

    private val mAdapter by lazy {
        val multiBindingAdapter = MultiBindingAdapter<ImageVideoItemViewModel>()
        val item_type_image = 0
        val item_type_video = item_type_image + 1
        multiBindingAdapter.addBindingItem(DefaultViewBindingItem<ImageVideoItemViewModel.ImageItemViewModel>(
            item_type_image,
            { item -> item is ImageVideoItemViewModel.ImageItemViewModel },
            { ItemImageBindingImpl.inflate(LayoutInflater.from(it.context), it, false) },
            { binding, item, cacheItemViewModel ->
                item
            }
        ))

        multiBindingAdapter.addBindingItem(DefaultViewBindingItem<ImageVideoItemViewModel.VideoItemViewModel>(
            item_type_video,
            { item -> item is ImageVideoItemViewModel.VideoItemViewModel },
            { ItemVideoBinding.inflate(LayoutInflater.from(it.context), it, false) },
            { binding, item, cacheItemViewModel ->
                item
            }
        ))

        multiBindingAdapter
    }

    init {
        adapter = mAdapter
        metro = item
    }


    override fun onVideo(view: View) {
        ARouter.getInstance()
            .build(RouterPath.VideoDetails.activity_video_details)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }
}