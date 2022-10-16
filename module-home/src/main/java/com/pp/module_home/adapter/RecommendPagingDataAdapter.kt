package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_ui.databinding.ItemHeader5Binding
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.library_ui.databinding.ItemVideoCardBinding
import com.pp.library_ui.databinding.ItemVideoSmallCardBinding
import com.pp.module_home.api.bean.RecommendBean
import com.pp.module_home.model.RecommendVideoCardItemViewModel
import com.pp.module_home.model.RecommendVideoSmallCardItemViewModel

class RecommendPagingDataAdapter : BindingPagingDataAdapter<ViewDataBinding, Any, RecommendBean.Item>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "RecommendAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecommendBean.Item>() {

            override fun areItemsTheSame(oldItem: RecommendBean.Item, newItem: RecommendBean.Item) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RecommendBean.Item,
                newItem: RecommendBean.Item
            ) = oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getRecommendItemType(item)
    }

    private fun getRecommendItemType(item: RecommendBean.Item?): Int {

        return when (item?.type) {
            // videoSmallCard ==>> 根据 item.type
            EyepetizerService.ItemType.videoSmallCard ->
                EyepetizerService.ItemType.getItemType(item?.type)
            // textCard ==>> 根据 item.data.type 判断类型
            EyepetizerService.ItemType.textCard ->
                EyepetizerService.ItemType.getItemType(item.data.type)

            // followCard ==>> 根据 item.data.content.type
            EyepetizerService.ItemType.followCard ->
                EyepetizerService.ItemType.getItemType(item.data.content.type)

            /*
                else ->
                    EyepetizerService.ItemDataType.getItemDataType(item?.data?.dataType ?: "unknown")*/
            else ->
                EyepetizerService.ItemType.getItemType(item?.type ?: "unknown")
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: RecommendBean.Item?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            // header5
            is ItemHeader5Binding -> "${item?.data?.text}"
            // content video
            is ItemVideoCardBinding -> RecommendVideoCardItemViewModel(item)
            // videoSmallCard
            is ItemVideoSmallCardBinding -> RecommendVideoSmallCardItemViewModel(item)
            else -> """  
                        item type: ${item?.type}
                        item header text: ${item?.data?.text}
                        item data type: ${item?.data?.type}
                        item data dataType: ${item?.data?.dataType}
                        item data title: ${item?.data?.title}
                        content type: ${item?.data?.content?.type}
                        content data type: ${item?.data?.content?.data?.type ?: "null"}
                        content data dataType: ${item?.data?.content?.data?.dataType ?: "null"}
                        content data title: ${item?.data?.content?.data?.title ?: "null"}
            """.trimIndent()
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            // header5
            EyepetizerService.ItemType.HEADER_5 ->
                ItemHeader5Binding.inflate(layoutInflater, parent, false)
            // videoSmallCard
            EyepetizerService.ItemType.VIDEO_SMALL_CARD ->
                ItemVideoSmallCardBinding.inflate(layoutInflater, parent, false)
            // content video
            EyepetizerService.ItemType.VIDEO ->
                ItemVideoCardBinding.inflate(layoutInflater, parent, false)

            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
    }

}