package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.module_home.api.bean.FeedBean
import com.pp.module_home.databinding.ItemDailyBinding
import com.pp.module_home.databinding.ItemTestBinding
import com.pp.module_home.databinding.ItemTextCardBinding
import com.pp.module_home.model.DailyItemViewModel

class DailyAdapter : BindingAdapter<ViewDataBinding, Any, FeedBean.Item>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "DailyAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FeedBean.Item>() {

            override fun areItemsTheSame(oldItem: FeedBean.Item, newItem: FeedBean.Item) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FeedBean.Item, newItem: FeedBean.Item) =
                oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getDailyItemType(item)
    }

    private fun getDailyItemType(item: FeedBean.Item?): Int {
        return when (item?.type) {
            // textCard ==>> 根据 item.data.type 判断类型
            EyepetizerService.ItemType.textCard ->
                EyepetizerService.ItemType.getItemType(item.data.type)

            // followCard,squareCardCollection等 ==>> 根据 item.data.dataType 判断类型
            else ->
                EyepetizerService.ItemDataType.getItemDataType(item?.data?.dataType ?: "unknown")
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: FeedBean.Item?,
        cacheItemViewModel: Any?
    ): Any {

        return cacheItemViewModel ?: when (binding) {
            is ItemTestBinding -> "${item?.type}     ${item?.data?.text ?: ""}"
            // type = header5
            is ItemTextCardBinding -> item?.data?.text ?: ""

            else -> DailyItemViewModel(item)
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            EyepetizerService.ItemType.UNKNOWN
            -> ItemTestBinding.inflate(layoutInflater, parent, false)
            // type = header5
            EyepetizerService.ItemType.HEADER_5
            -> ItemTextCardBinding.inflate(layoutInflater, parent, false)
            // dataType = FollowCard
            else -> ItemDailyBinding.inflate(layoutInflater, parent, false)
        }
    }

}