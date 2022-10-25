package com.pp.module_home.model

import android.content.Context
import android.view.ViewGroup
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel
import com.pp.module_home.api.bean.FollowBean.Item
import java.text.SimpleDateFormat


class FollowItemViewModel(item: Item?,val context: Context) :
    FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    init {
        val contentData = item?.data?.content?.data
        icon.set(contentData?.author?.icon)
        author.set(contentData?.author?.name)
        date.set(format.format(contentData?.date))
        content.set("     ${contentData?.description}")
        cover.set(contentData?.cover?.feed)
        category.set(contentData?.category)

        collectionCount.set(contentData?.consumption?.collectionCount.toString() ?: "0")
        realCollectionCount.set(contentData?.consumption?.realCollectionCount.toString() ?: "0")
        replyCount.set(contentData?.consumption?.replyCount.toString() ?: "0")

        adapter = Adapter().apply {
            setDataList(listOf(ImageVideoItemViewModel(cover, true)))
        }

    }

    companion object {
        val format by lazy { SimpleDateFormat("yyyy.MM.dd") }
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