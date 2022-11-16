package com.pp.module_video_details.ui

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.mvvm.LifecycleViewModel

class IntroductionViewModel(val details: MetroDataBean?, app: Application) :
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

    suspend fun getRelatedRecommend() {
        details?.run {
            EyepetizerService2.itemApi
                .getRelatedRecommend(
                    resourceId,
                    resourceType
                )
                .let {
                    Log.e("TAG", "getRelatedRecommend size: ${it.result?.itemList?.size}")
                }


        }

    }
}
