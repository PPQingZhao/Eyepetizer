package com.pp.module_discovery.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.module_discovery.repository.DiscoveryRepository
import com.pp.library_base.base.ThemeViewModel
import kotlinx.coroutines.launch

class DiscoveryViewModel(app: Application) : ThemeViewModel(app) {

    private val _dataList = MutableLiveData<List<Any>>()
    val dataList = _dataList

    fun getData() {
        viewModelScope.launch {
            try {
                val response = DiscoveryRepository.getData()
                if (response.code == 0) {
                    val list = mutableListOf<Any>()
                    response.result?.cardList?.forEach { card ->
                        if (card.cardData.header.left.isNotEmpty()
                            || card.cardData.header.right.isNotEmpty()
                            || card.cardData.header.center.isNotEmpty()
                        ) {
                            list.add(card.cardData.header)
                        }
                        when (card.type) {
                            EyepetizerService2.CardType.SET_METRO_LIST -> {

                                card.cardData.body.metroList?.forEach {
                                    when (it.style.tplLabel) {
                                        EyepetizerService2.MetroType.Style.default_web,
                                        EyepetizerService2.MetroType.Style.stacked_slide_cover_image,
                                        EyepetizerService2.MetroType.Style.icon_grid -> {
                                            list.add(it)
                                        }
                                    }

                                }
                            }
                            EyepetizerService2.CardType.SET_SLIDE_METRO_LIST -> {
                                list.add(card)
                            }
                        }
                    }
                    _dataList.value = list
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}