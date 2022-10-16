package com.pp.module_home.model

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel
import com.pp.module_home.api.bean.FollowBean.Item
import java.text.SimpleDateFormat


class FollowItemViewModel(item: Item?, context: Context) :
    FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    init {
        val contentData = item?.data?.content?.data
        icon = contentData?.author?.icon
        author = contentData?.author?.name
        date = format.format(contentData?.date)
        content = "     ${contentData?.description}"
        feed = contentData?.cover?.feed
        category = contentData?.category

        collectionCount.set(contentData?.consumption?.collectionCount.toString() ?: "0")
        realCollectionCount.set(contentData?.consumption?.realCollectionCount.toString() ?: "0")
        replyCount.set(contentData?.consumption?.replyCount.toString() ?: "0")

        layoutManager = LinearLayoutManager(context)
        adapter = Adapter().apply {
            setDataList(listOf(ImageVideoItemViewModel(feed, true)))
        }

    }

    companion object {
        val format by lazy { SimpleDateFormat("yyyy.MM.dd") }
    }

    inner class Adapter : BindingAdapter<ItemImageVideoBinding, ImageVideoItemViewModel, ImageVideoItemViewModel>() {
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