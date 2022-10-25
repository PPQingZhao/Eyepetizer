package com.pp.module_home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.pagingsource.ItemModel
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.library_ui.databinding.ItemVideoCardBinding

class DailyPagingDataAdapter2 :
    BindingPagingDataAdapter<ViewDataBinding, Any, ItemModel<Any>>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "DailyAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemModel<Any>>() {

            override fun areItemsTheSame(oldItem: ItemModel<Any>, newItem: ItemModel<Any>) =
                oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ItemModel<Any>, newItem: ItemModel<Any>) =
                oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getDailyItemType(item)
    }

    val item_type_unknown = 0
    val item_type_daily = item_type_unknown + 1
    private fun getDailyItemType(item: ItemModel<Any>?): Int {
        val data = item?.data
        return when (data) {
            is PageDataBean.Card.CardData.Body.Metro -> item_type_daily
            else -> item_type_unknown
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: ItemModel<Any>?,
        cacheItemViewModel: Any?
    ): Any {

        return cacheItemViewModel ?: when (binding) {
            //  video
            is ItemVideoCardBinding -> MetroLargeVideoCardItemViewModel(item?.data as PageDataBean.Card.CardData.Body.Metro)
            // 待开放功能 unknown等
            else -> {
                val data = item?.data
                if (data is PageDataBean.Card.CardData.Body.Metro) {
                    """
                        ${data.type}
                        ${data.style?.tplLabel}
                        ${data.metroData?.title ?: "null"}
                    """.trimIndent()
                } else {
                    "unknown"
                }
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            // type = video
            item_type_daily ->
                ItemVideoCardBinding.inflate(layoutInflater, parent, false)
            // 待开放功能
            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
    }

}