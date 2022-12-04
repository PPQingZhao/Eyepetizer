package com.pp.module_discovery.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import kotlinx.coroutines.flow.Flow

object TagDetailRepository {
    const val TAG = "TagDetailRepository"

    suspend fun getTagDetail(id: String): TagDetailBean {
        return EyepetizerService.discoverApi.getTagDetail(EyepetizerService.URL_TAG, id)
    }

    suspend fun getDynamics(url: String) {

    }

    fun getVideoPagingData(url: String): Flow<PagingData<VideoBean.Item>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { VideoPagingSource(url) }).flow
    }

    class VideoPagingSource(private val firstUrl: String) : PagingSource<String, VideoBean.Item>() {
        override suspend fun load(params: LoadParams<String>): LoadResult<String, VideoBean.Item> {

            return try {
                val url = params.key ?: firstUrl
                val videoBean = EyepetizerService.discoverApi.getVideos(url)
                val value = videoBean.itemList
                val preKey = null
                val nextKey = videoBean.nextPageUrl
                LoadResult.Page<String, VideoBean.Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                Log.e(TAG, "load error: ${e.message}")
                LoadResult.Error(e)
            }
        }
    }
}