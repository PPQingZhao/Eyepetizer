package com.pp.library_common.model

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_ui.adapter.MultiBindingAdapter
import com.pp.library_ui.model.ItemRecyclerViewModel

class CardRecyclerViewModel(cardBean: Card?, context: Context) : ItemRecyclerViewModel() {

    var card: Card? = null
        set(value) {
            field = value
            card?.cardData?.body?.metroList?.forEach {
                when (it.style.tplLabel) {
                    EyepetizerService2.MetroType.Style.slide_cover_image_with_title -> {
                        dimensionRatio.set("16:9")
                    }
                    EyepetizerService2.MetroType.Style.slide_cover_image -> {
                        dimensionRatio.set("4:3")
                    }
                }
            }
            adapter?.setDataList(card?.cardData?.body?.metroList ?: mutableListOf())
        }

    init {
        adapter = MultiBindingAdapter<Any>()

        val layoutInflater = LayoutInflater.from(context)
        adapter!!.addBindingItem(
            MetroPagingDataAdapterType.slide_cover_image_with_title(
                layoutInflater
            )
        )
        adapter!!.addBindingItem(MetroPagingDataAdapterType.slide_cover_image(layoutInflater))
        card = cardBean
    }

    override fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

}