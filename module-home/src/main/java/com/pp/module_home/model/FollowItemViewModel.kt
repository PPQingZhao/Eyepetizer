package com.pp.module_home.model

import android.view.View
import androidx.databinding.ObservableField
import com.pp.module_home.api.bean.FollowBean.Item
import java.text.SimpleDateFormat


class FollowItemViewModel(val item: Item?) {

    val content: String?
    val icon: String?
    val author: String?
    val date: String?
    val feed: String?
    val category: String?
    val expand: ObservableField<Boolean> = ObservableField(false)
    val collectionCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()

    init {
        val contentData = item?.data?.content?.data
        icon = contentData?.author?.icon
        author = contentData?.author?.name
        date = format.format(contentData?.date)
        content = contentData?.description
        feed = contentData?.cover?.feed
        category = contentData?.category

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