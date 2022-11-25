package com.pp.module_video_details.ui

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.paging.PagingData
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.module_video_details.repository.RelatedRecommendRepository
import com.pp.library_base.base.ThemeViewModel
import kotlinx.coroutines.flow.Flow

class IntroductionViewModel(val details: MetroDataBean?, app: Application) :
    ThemeViewModel(app) {

    val title = ObservableField<String>()
    val category = ObservableField<String>()
    val content = ObservableField<String>()
    val collectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val shareCount = ObservableField<String>()

    init {

        details?.let {
            title.set(it.video.title)

            var topic = ""
            it.topics.forEach {
                topic = "${topic}${it.title} "
            }

            category.set(topic)
            content.set(it.text)
            collectionCount.set(it.consumption.likeCount.toString())
            replyCount.set(it.consumption.commentCount.toString())
            shareCount.set(it.consumption.shareCount.toString())
//            Log.e("TAG", "${details.resourceId}   ${details.resourceType}")
        }
    }

    fun getRelatedRecommend(): Flow<PagingData<Metro>> {
        return RelatedRecommendRepository.getPagingData(details?.resourceId, details?.resourceType)
    }
}
