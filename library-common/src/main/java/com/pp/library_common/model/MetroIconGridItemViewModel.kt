package com.pp.library_common.model

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.pp.library_ui.R
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.IconBean
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.library_ui.model.ItemRecyclerViewModel

class MetroIconGridItemViewModel(m: Metro?, context: Context): ItemRecyclerViewModel() {

    var metro: Metro? = null
        set(value) {
            field = value
            val list = mutableListOf<IconBean>()
            if (value?.style?.tplLabel == EyepetizerService2.MetroType.Style.icon_grid) {
                value.metroData.icons?.forEach {
                    list.add(it)
                }
            }
            adapter?.setDataList(list)
        }

    init {
        adapter = MultiBindingAdapter()
        val layoutInflater = LayoutInflater.from(context)
        adapter!!.addBindingItem(MetroPagingDataAdapterType.icon_item(layoutInflater))

        metro = m
        bg.set(R.drawable.shape_rectangle_gradient_white)
    }

    override fun getLayoutManager(context: Context): LayoutManager {
        return GridLayoutManager(context, 3)
    }
}