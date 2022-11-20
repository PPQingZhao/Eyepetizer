package com.pp.library_common.model

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.library_ui.model.ItemRecyclerViewModel
import com.pp.library_ui.widget.layoutmanager.CardLayoutManager

class MetroCardRecyclerViewModel(metroBean: Metro?, context: Context): ItemRecyclerViewModel() {

    var metro: Metro? = null
        set(value) {
            field = value
            val list = mutableListOf<MetroDataBean.Item>()
            value?.metroData?.itemList?.forEach {
                list.add(it)
            }
            adapter?.run {
                setDataList(list)
            }
        }

    init {
        adapter = MultiBindingAdapter<Any>()
        val layoutInflater = LayoutInflater.from(context)
        adapter!!.addBindingItem(MetroPagingDataAdapterType.stacked_slide_item(layoutInflater))
        metro = metroBean
    }

    override fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return CardLayoutManager()
    }

}