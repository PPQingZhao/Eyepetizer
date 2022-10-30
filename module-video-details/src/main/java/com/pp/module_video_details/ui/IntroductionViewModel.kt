package com.pp.module_video_details.ui

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.ItemDetailsBean
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IntroductionViewModel(val details: ItemDetailsBean?, app: Application) :
    LifecycleViewModel(app) {

    val title = ObservableField<String>()
    val category = ObservableField<String>()
    val content = ObservableField<String>()
    val collectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val shareCount = ObservableField<String>()

    init {

        details?.let {
            title.set(it.video.title)
            category.set("# ${it.category.name}")
            content.set(it.text)
            collectionCount.set(it.consumption.likeCount.toString())
            replyCount.set(it.consumption.commentCount.toString())
            shareCount.set(it.consumption.shareCount.toString())
            Log.e("TAG", "${details.resourceId}   ${details.resourceType}")
        }
    }


    fun getRelatedRecommend() {
        GlobalScope.launch(Dispatchers.IO) {
            EyepetizerService2.api
                .getRelatedRecommend(details?.resourceId?.toInt(), details?.resourceType)
                .let {
                    Log.e("TAG", "size: ${it.result.itemList.size}")
                }

        }
    }
}
