package com.pp.library_common.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.databinding.ItemBannerContentBinding
import com.pp.library_ui.model.BannerContentItemViewModel
import com.pp.library_ui.model.BannerListItemViewModel
import com.pp.library_ui.widget.banner.BaseBannerAdapter

open class MetroBannerItemViewModel(
    card: Card? = null,
    metroList: List<Metro>?
) : BannerListItemViewModel<BannerContentItemViewModel, MetroBannerItemViewModel.BannerViewHolder<ItemBannerContentBinding>>() {
    var card: Card? = null
    set(value) {
        field = value
    }
    var metroList: List<Metro>? = null
    set(value) {
        field = value

        itemList.clear()

        value?.forEach { metro ->
            itemList.add(BannerContentItemViewModel(metro.metroData.cover.url))
        }

        bannerAdapter?.setData(itemList)
    }

    private val itemList = mutableListOf<BannerContentItemViewModel>()
    init {
        bannerAdapter = object :
            BaseBannerAdapter<BannerContentItemViewModel, BannerViewHolder<ItemBannerContentBinding>>() {


            override fun onBind(
                holder: BannerViewHolder<ItemBannerContentBinding>,
                data: BannerContentItemViewModel,
                realPosition: Int,
                size: Int
            ) {
                holder.binding.viewModel = data
                holder.binding.executePendingBindings()
            }

            override fun createViewHolder(
                parent: ViewGroup,
                inflater: LayoutInflater,
                viewType: Int
            ): BannerViewHolder<ItemBannerContentBinding> {
                val binding = ItemBannerContentBinding.inflate(inflater, parent, false)
                return BannerViewHolder(binding)
            }
        }

        // 初始化数据
        this.metroList = metroList

    }

    class BannerViewHolder<VB : ViewDataBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)

}