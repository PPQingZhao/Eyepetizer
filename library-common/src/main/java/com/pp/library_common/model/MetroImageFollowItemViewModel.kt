package com.pp.library_common.model

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_common.extension.setMetro
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.library_ui.databinding.ItemImageBindingImpl
import com.pp.library_ui.model.FollowCardItemViewModel
import com.pp.library_ui.model.ImageVideoItemViewModel

open class MetroImageFollowItemViewModel(
    item: Metro?,
    private val mine: Boolean = false,
) : FollowCardItemViewModel.ImageFollowCardItemViewModel() {

    companion object {
        private const val TAG = "MetroFollowItem"
    }

    var resourceId: Long? = null
    var resourceType: String? = null

    var metro: Metro? = null
        set(value) {
            field = value

            setMetro(value, mine)

            val metroData = value?.metroData
            resourceId = metroData?.resourceId
            resourceType = metroData?.resourceType

            dataList.clear()

            metroData?.images?.forEach { it ->
                it.cover.apply {
                    dataList.add(
                        ImageVideoItemViewModel.ImageItemViewModel<MetroDataBean?>(
                            ObservableField(url),
                            metroData
                        )
                    )
                }
            }

            indicatorCount.set(dataList.size)
        }

    private val mAdapter by lazy {
        val multiBindingAdapter = MultiBindingAdapter<ImageVideoItemViewModel<MetroDataBean?>>()
        val item_type_image = 0
        multiBindingAdapter.addBindingItem(DefaultViewBindingItem(
            item_type_image,
            { item -> item is ImageVideoItemViewModel.ImageItemViewModel },
            { ItemImageBindingImpl.inflate(LayoutInflater.from(it.context), it, false) },
            { binding, item, cacheItemViewModel ->
                binding.root.setOnClickListener {
                    ARouter.getInstance()
                        .build(RouterPath.ItemDetails.activity_image_details)
                        .withLong("resourceId", item?.data?.resourceId ?: 0)
                        .withString("resourceType", item?.data?.resourceType ?: "")
                        .navigation()
                }
                item
            }
        ))

        multiBindingAdapter
    }

    init {
        adapter = mAdapter

        this.metro = item
    }

    override fun onItemClick(view: View) {
        ARouter.getInstance()
            .build(RouterPath.ItemDetails.activity_image_details)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
    }
}