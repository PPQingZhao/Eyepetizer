package com.pp.library_common.model

import android.os.SystemClock
import android.view.ViewGroup
import com.pp.library_network.eyepetizer.EyepetizerApi
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel
import io.reactivex.schedulers.Schedulers


class MetroFollowItemViewModel(
    metro: PageDataBean.Card.CardData.Body.Metro?,
) : FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    init {

        val metroData = metro?.metroData

        EyepetizerApi.api.getItemDetails(metroData?.resourceId, metroData?.resourceType)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {

                if (it.code != EyepetizerService2.ErrorCode.SUCCESS) {
                    return@subscribe
                }
                it.result.run {
                    val startTime = SystemClock.currentThreadTimeMillis()

                    this@MetroFollowItemViewModel.icon.set(this.author.avatar.url)
                    this@MetroFollowItemViewModel.author.set(this.author.nick)
                    this@MetroFollowItemViewModel.cover.set(this.video.cover.url)
                    this@MetroFollowItemViewModel.date.set(this.publishTime)
//                    this@MetroFollowItemViewModel.area.set(this.realLocation)
                    this@MetroFollowItemViewModel.content.set(this.text)
                    this@MetroFollowItemViewModel.category.set(this.category.name)


                    this@MetroFollowItemViewModel.collectionCount
                        .set(this.consumption.likeCount.toString())
                    this@MetroFollowItemViewModel.realCollectionCount
                        .set(this.consumption.collectionCount.toString())
                    this@MetroFollowItemViewModel.replyCount
                        .set(this.consumption.commentCount.toString())

                    val endTime = SystemClock.currentThreadTimeMillis()
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
        ): ImageVideoItemViewModel {
            return cacheItemViewModel ?: item ?: ImageVideoItemViewModel(null)
        }

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemImageVideoBinding {
            return ItemImageVideoBinding.inflate(layoutInflater, parent, false)
        }

    }
}