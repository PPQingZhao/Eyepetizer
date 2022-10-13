package com.pp.module_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.module_home.api.bean.RecommendBean
import com.pp.module_home.databinding.ItemRecommndBinding
import com.pp.module_home.model.RecommendItemViewModel

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

        return if ("textCard" == item?.type) 0 else 0
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: RecommendBean.Item?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            else -> RecommendItemViewModel(item)
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemRecommndBinding.inflate(LayoutInflater.from(parent.context))
    }

}