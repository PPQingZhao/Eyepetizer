package com.pp.library_common.model

import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel
import kotlinx.coroutines.*


@OptIn(DelicateCoroutinesApi::class)
open class MetroFollowItemViewModel(
    val metro: PageDataBean.Card.CardData.Body.Metro?,
) : FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    var resourceId: Int?
    var resourceType: String?

    init {

        val metroData = metro?.metroData
        resourceId = metroData?.resourceId
        resourceType = metroData?.resourceType

        GlobalScope.launch(Dispatchers.IO) {

            val response = EyepetizerService2.api.getItemDetails(resourceId, resourceType)

//                {"code":40001,"message":{"content":"当前作品不可见","action":"toast"},"result":{"status":false}}
            if (response.code != EyepetizerService2.ErrorCode.SUCCESS) {
                this@MetroFollowItemViewModel.content.set(response.message?.content)
                cancel()
                return@launch
            }
            response.result.run {

                try {

                this@MetroFollowItemViewModel.icon.set(this.author.avatar.url)
                this@MetroFollowItemViewModel.author.set(this.author.nick)
                this@MetroFollowItemViewModel.cover.set(this.video.cover.url)
                this@MetroFollowItemViewModel.date.set(this.rawPublishTime)
//                    this@MetroFollowItemViewModel.area.set(this.realLocation)
                this@MetroFollowItemViewModel.content.set(this.text)
                this@MetroFollowItemViewModel.category.set(this.category.name)


                this@MetroFollowItemViewModel.collectionCount
                    .set(this.consumption.likeCount.toString())
                this@MetroFollowItemViewModel.realCollectionCount
                    .set(this.consumption.collectionCount.toString())
                this@MetroFollowItemViewModel.replyCount
                    .set(this.consumption.commentCount.toString())
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }
        }

        adapter = Adapter().apply {
            setDataList(listOf(ImageVideoItemViewModel(cover, true)))
        }

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
            .withInt("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }
}