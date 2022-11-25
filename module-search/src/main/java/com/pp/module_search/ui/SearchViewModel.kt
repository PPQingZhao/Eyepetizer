package com.pp.module_search.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.HotQueriesBean
import com.pp.module_search.repository.SearchRepository
import com.pp.library_base.base.ThemeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.pp.library_common.result.Result
import com.pp.module_search.listener.OnItemClickListener
import com.pp.module_search.model.SearchItemModel
import com.pp.module_search.model.SearchRankItemModel

class SearchViewModel(app: Application): ThemeViewModel(app) {

    private val _hotQuery = MutableLiveData<List<SearchItemModel>>()
    val hotQueriesData = _hotQuery

    private val _recommendState = MutableStateFlow<Result<List<MultiItemEntity>>>(Result.Loading)
    val recommendState = _recommendState

    fun getHot(listener: OnItemClickListener) {
        viewModelScope.launch {
            try {
                val response = SearchRepository.getHotQueries()
                val hotList = mutableListOf<SearchItemModel>()
                if (response.code == 0) {
                    response.result?.itemList?.forEach {
                        val item = SearchItemModel(SearchActivity.TYPE_HOT_QUERIES, it, listener)
                        hotList.add(item)
                    }
                    _hotQuery.value = hotList
                }

            } catch (e: Exception) {
                Log.e(TAG, "getHot err: ${e.message}")
            }
        }
    }

    suspend fun getHotQueries(): BaseResponse<HotQueriesBean> {
        return SearchRepository.getHotQueries()
    }

    fun getRecommend() {
        viewModelScope.launch {
            try {
                val response = SearchRepository.getRecommend()
                if (response.code == 0) {
                    val itemList = mutableListOf<MultiItemEntity>()
                    response.result?.cardList?.forEach {
                        /*it.cardData.header.left?.forEach { headMetro ->
                            itemList.add(
                                SearchRankItemTitleModel(
                                    headMetro.metroData.text,
                                    TYPE_RANK_TITLE
                                )
                            )
                        }*/
                        it.cardData.body.metroList?.forEach { bodyMetro ->
                            itemList.add(SearchRankItemModel(bodyMetro, TYPE_RANK))
                        }
                    }
                    _recommendState.value = Result.Success(itemList)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getRecommend err: ${e.message}")
            }
        }
    }

    companion object {
        private val TAG = "SearchViewModel"
        const val TYPE_RANK_TITLE = 0x10
        const val TYPE_RANK = 0x11
    }
}