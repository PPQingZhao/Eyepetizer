package com.pp.module_discovery.ui.tag

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.module_discovery.repository.TagDetailRepository
import kotlinx.coroutines.launch

class TagDetailViewModel(app: Application): ThemeViewModel(app) {

    companion object {
        private const val TAG = "TagDetailViewModel"
    }

    val title by lazy { MutableLiveData("") }
    val description by lazy { MutableLiveData("") }
    val bgPicture by lazy { MutableLiveData("") }
    val countText by lazy { MutableLiveData("") }
    val followed by lazy { MutableLiveData(false) }

    val tabInfoData by lazy { MutableLiveData<TagDetailBean.TabInfo>() }

    private var shareLink = ""

    fun getDetail(id: String) {
        viewModelScope.launch {
            try {
                val response = TagDetailRepository.getTagDetail(id)
                response.run {
                    bgPicture.value = tagInfo.bgPicture
                    title.value = tagInfo.name
                    description.value = tagInfo.description
                    followed.value = tagInfo.follow.followed
                    val followCount = tagInfo.tagFollowCount
                    val lookCount = tagInfo.lookCount
                    shareLink = tagInfo.shareLinkUrl

                    countText.value = "$followCount 人关注| $lookCount 人参与"

                    tabInfoData.value = tabInfo

                }
            } catch (e: Exception) {
                Log.e(TAG, "getDetail err: ${e.message}")
            }
        }
    }

}