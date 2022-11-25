package com.pp.module_discovery.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pp.library_common.app.App
import com.pp.module_discovery.R
import com.pp.module_discovery.repository.DiscoveryRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.launch

class TagDetailViewModel(app: Application): LifecycleViewModel(app) {

    companion object {
        private const val TAG = "TagDetailViewModel"
    }

    val title by lazy { MutableLiveData("") }
    val description by lazy { MutableLiveData("") }
    val bgPicture by lazy { MutableLiveData("") }
    val countText by lazy { MutableLiveData("") }
    val followed by lazy { MutableLiveData(false) }

    fun getDetail(id: String) {
//        App.getInstance().getString(R.string.)
        viewModelScope.launch {
            try {
                val response = DiscoveryRepository.getTagDetail(id)
                response.run {
                    bgPicture.value = tagInfo.bgPicture
                    title.value = tagInfo.name
                    description.value = tagInfo.description
                    followed.value = tagInfo.follow.followed
                    val followCount = tagInfo.tagFollowCount
                    val lookCount = tagInfo.lookCount

//                    countText.value =
                }
            } catch (e: Exception) {
                Log.e(TAG, "getDetail err: ${e.message}")
            }
        }
    }

}