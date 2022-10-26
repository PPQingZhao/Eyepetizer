package com.pp.module_video_details

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.ItemDetailsBean
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VideoDetailsVideoModel(app: Application) : LifecycleViewModel(app) {

    private val itemDetails = MutableLiveData<ItemDetailsBean?>()
    val icon = ObservableField<String>()
    val nick = ObservableField<String>()
    val cover = ObservableField<String>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        itemDetails.observe(owner) {
            icon.set(it?.author?.avatar?.url)
            nick.set(it?.author?.nick)
            cover.set(it?.video?.cover?.url)
        }
    }

    /**
     * 加载 item details
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun getItemDetails(resourceId: Int?, resourceType: String?): LiveData<ItemDetailsBean?> {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val itemDetails = EyepetizerService2.api.getItemDetails(resourceId, resourceType)
                this@VideoDetailsVideoModel.itemDetails.postValue(itemDetails.result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return itemDetails
    }

}
