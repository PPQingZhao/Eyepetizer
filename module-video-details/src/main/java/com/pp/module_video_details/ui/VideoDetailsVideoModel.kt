package com.pp.module_video_details.ui

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_base.base.ThemeViewModel
import kotlinx.coroutines.*

class VideoDetailsVideoModel(app: Application) : ThemeViewModel(app) {

    private val itemDetails = MutableLiveData<MetroDataBean?>()
    val icon = ObservableField<String>()
    val nick = ObservableField<String>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        itemDetails.observe(owner) {
            icon.set(it?.author?.avatar?.url)
            nick.set(it?.author?.nick)
        }
    }

    fun getItemDetails(
        resourceId: Long?,
        resourceType: String?,
    ): LiveData<MetroDataBean?> {

        itemDetails.value = null
        return getItemDetailsWithCache(resourceId, resourceType)
    }

    /**
     * 加载 item details
     */
    fun getItemDetailsWithCache(resourceId: Long?, resourceType: String?): LiveData<MetroDataBean?> {

        if (itemDetails.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val itemDetails =
                        EyepetizerService2.itemApi.getItemDetails(resourceId, resourceType)
                    withContext(Dispatchers.Main) {
                        this@VideoDetailsVideoModel.itemDetails.value = itemDetails.result
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return itemDetails
    }

}
