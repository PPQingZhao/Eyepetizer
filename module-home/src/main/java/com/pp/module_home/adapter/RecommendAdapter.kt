package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.module_home.api.bean.RecommendBean
import com.pp.module_home.databinding.ItemFollowCardBinding
import com.pp.module_home.databinding.ItemHeader5Binding
import com.pp.module_home.databinding.ItemVideoSmallCardBinding
import com.pp.module_home.model.RecommendFollowCardItemViewModel
import com.pp.module_home.model.RecommendVideoSmallCardItemViewModel

class RecommendAdapter : BindingAdapter<ViewDataBinding, Any, RecommendBean.Item>(DIFF_CALLBACK) {

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
            // textCard ==>> 根据 item.data.type 判断类型
            EyepetizerService.ItemType.textCard ->
                EyepetizerService.ItemType.getItemType(item.data.type)

            /*       // FollowCard 等 ==>> 根据 item.data.dataType 判断类型
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
            // followCad
            is ItemFollowCardBinding -> RecommendFollowCardItemViewModel(item)
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
            // followCard
            EyepetizerService.ItemType.FOLLOW_CARD ->
                ItemFollowCardBinding.inflate(layoutInflater, parent, false)
            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
    }

}