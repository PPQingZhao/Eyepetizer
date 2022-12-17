package com.pp.module_discovery.ui.topic

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_network.eyepetizer.bean.nav.Nav
import com.pp.module_discovery.repository.TopicSquareRepository
import kotlinx.coroutines.launch

class TopicSquareViewModel(app: Application) : ThemeViewModel(app) {
    private val TAG = "TopicSquareViewModel"

    val navTabList = MutableLiveData<List<Nav>>()

    fun getTopicNav(type: String) {
        viewModelScope.launch {
            try {
                val response = TopicSquareRepository.getTopicNav(type)
                response.result?.let {
                    val list = mutableListOf<Nav>()
                    it.navList.forEach {
                        list.add(it)
                    }
                    navTabList.value = list
                }
            } catch (e: Exception) {
                Log.e(TAG, "getTopicNav err: ${e.message}")
            }
        }
    }
}