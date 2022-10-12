package com.pp.module_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.module_home.api.bean.RecommendBean
import com.pp.module_home.R
import com.pp.module_home.model.RecommendItemViewModel

class RecommendAdapter : BindingAdapter<Any, RecommendBean.Item>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "RecommendAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecommendBean.Item>() {

            override fun areItemsTheSame(oldItem: RecommendBean.Item, newItem: RecommendBean.Item) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RecommendBean.Item, newItem: RecommendBean.Item) = oldItem == newItem
        }
    }

    override fun createItemViewModel(
        itemViewType: Int,
        item: RecommendBean.Item?,
        cacheItemViewModel: Any?
    ): Any? {
        return cacheItemViewModel ?: RecommendItemViewModel(item)
    }

    override fun createItemView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_recommnd, parent, false)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getRecommendItemType(item)
    }

    private fun getRecommendItemType(item: RecommendBean.Item?): Int {
        return if ("textCard" == item?.type) 0 else 0
    }

}