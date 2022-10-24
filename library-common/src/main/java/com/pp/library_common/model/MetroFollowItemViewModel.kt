package com.pp.library_common.model

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_network.eyepetizer.ApiService
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel
import io.reactivex.schedulers.Schedulers


class MetroFollowItemViewModel(metro: PageDataBean.Card.CardData.Body.Metro?, context: Context) :
    FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    init {

        val metroData = metro?.metroData


        icon.set(metroData?.author?.avatar?.url)
        author.set(metroData?.author?.nick)
        cover.set(metroData?.cover?.url)

        ApiService.api.getItemDetails(metroData?.resourceId ?: -1, metroData?.resourceType ?: "")
            .subscribeOn(Schedulers.io())
            .subscribe {

                it?.result?.run {
                    this@MetroFollowItemViewModel.date.set(this.publishTime)
//                    this@MetroFollowItemViewModel.area.set(this.realLocation)
                    this@MetroFollowItemViewModel.content.set(this.text)
                    this@MetroFollowItemViewModel.category.set(this.category.name)

                    this@MetroFollowItemViewModel.collectionCount.set(
                        this.consumption.likeCount.toString()
                    )
                    this@MetroFollowItemViewModel.realCollectionCount.set(
                        this.consumption.collectionCount.toString()
                    )
                    this@MetroFollowItemViewModel.replyCount.set(
                        this.consumption.commentCount.toString()
                    )
                }

            }


        layoutManager = LinearLayoutManager(context)
        adapter = Adapter().apply {
            setDataList(listOf(ImageVideoItemViewModel(cover.get(), true)))
        }

    }

    inner class Adapter :
        BindingAdapter<ItemImageVideoBinding, ImageVideoItemViewModel, ImageVideoItemViewModel>() {
        override fun createViewModel(
            binding: ItemImageVideoBinding,
            item: ImageVideoItemViewModel?,
            cacheItemViewModel: ImageVideoItemViewModel?
        ): ImageVideoItemViewModel {
            return cacheItemViewModel ?: item ?: ImageVideoItemViewModel("")
        }

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemImageVideoBinding {
            return ItemImageVideoBinding.inflate(layoutInflater, parent, false)
        }

    }
}