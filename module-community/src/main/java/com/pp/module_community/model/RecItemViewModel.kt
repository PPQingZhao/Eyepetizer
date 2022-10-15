package com.pp.module_community.model

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.module_community.api.bean.CommunityRecBean
import java.text.SimpleDateFormat
import java.util.*

class RecItemViewModel(val item: CommunityRecBean.Item?) {

    val content: String?
    val icon: String?
    val author: String?
    val date: String?
    val area: String?
    val feed: String?
    val category: String?
    val expand: ObservableField<Boolean> = ObservableField(false)
    val collectionCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val videoType: ObservableField<Boolean> = ObservableField(false)

    init {
        val header = item?.data?.header
        val _content = item?.data?.content
        val contentData = item?.data?.content?.data
        icon = header?.icon
        author = header?.issuerName
        date = format.format(Date(header?.time?: 0))
        area = contentData?.city
        content = contentData?.description
        feed = contentData?.cover?.feed

        videoType.set(EyepetizerService.ContentType.isVideo(_content?.type))
        category = contentData?.tags?.getOrNull(0)?.name ?: "null"

        collectionCount.set(contentData?.consumption?.collectionCount.toString() ?: "0")
        realCollectionCount.set(contentData?.consumption?.realCollectionCount.toString() ?: "0")
        replyCount.set(contentData?.consumption?.replyCount.toString() ?: "0")
    }

    companion object {
        val format by lazy { SimpleDateFormat("yyyy.MM.dd") }
    }

    fun onExpand(view: View) {
        expand.set(expand.get()?.not())
    }
}