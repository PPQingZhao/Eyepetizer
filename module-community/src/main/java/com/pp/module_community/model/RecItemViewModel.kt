package com.pp.module_community.model

import android.content.Context
import android.view.ViewGroup
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel
import com.pp.module_community.api.bean.CommunityRecBean
import java.text.SimpleDateFormat
import java.util.*

class RecItemViewModel(val item: CommunityRecBean.Item?,val context: Context) :
    FollowCardItemViewModel<BindingHolder<ItemImageVideoBinding>>() {

    init {
        val header = item?.data?.header
        val _content = item?.data?.content
        val contentData = item?.data?.content?.data
        icon.set(header?.icon)
        author.set(header?.issuerName)
        date.set(format.format(Date(header?.time ?: 0)))
        area.set(contentData?.city)
        content.set(contentData?.description)
        cover.set(contentData?.cover?.feed)

        videoType = EyepetizerService.ContentType.isVideo(_content?.type)
        category.set(contentData?.tags?.getOrNull(0)?.name ?: "null")

        collectionCount.set(contentData?.consumption?.collectionCount.toString() ?: "0")
        realCollectionCount.set(contentData?.consumption?.realCollectionCount.toString() ?: "0")
        replyCount.set(contentData?.consumption?.replyCount.toString() ?: "0")

        adapter = ItemAdapter().apply {
            setDataList(listOf(ImageVideoItemViewModel(cover, videoType)))
        }
        adapter.apply {
        }
    }

    class ItemAdapter :
        BindingAdapter<ItemImageVideoBinding, ImageVideoItemViewModel, ImageVideoItemViewModel>() {

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemImageVideoBinding {
            return ItemImageVideoBinding.inflate(layoutInflater, parent, false)
        }

        override fun createViewModel(
            binding: ItemImageVideoBinding,
            item: ImageVideoItemViewModel?,
            cacheItemViewModel: ImageVideoItemViewModel?
        ): ImageVideoItemViewModel {
            return cacheItemViewModel ?: item ?: ImageVideoItemViewModel(null, false)
        }

    }

    companion object {
        val format by lazy { SimpleDateFormat("yyyy.MM.dd") }
    }
}