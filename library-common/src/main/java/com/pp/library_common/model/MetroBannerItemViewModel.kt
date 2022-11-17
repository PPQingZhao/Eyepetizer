package com.pp.library_common.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.databinding.ItemBannerContentBinding
import com.pp.library_ui.model.BannerContentItemViewModel
import com.pp.library_ui.model.BannerListItemViewModel
import com.pp.library_ui.widget.banner.BaseBannerAdapter

open class MetroBannerItemViewModel(
    val card: PageDataBean.Card? = null,
    val metroList: List<Metro>?
) : BannerListItemViewModel<BannerContentItemViewModel, MetroBannerItemViewModel.BannerViewHolder<ItemBannerContentBinding>>() {
    val itemList = mutableListOf<BannerContentItemViewModel>()

    init {

        metroList?.forEach { metro ->
            itemList.add(BannerContentItemViewModel(metro.metroData.cover.url))
        }

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

        bannerAdapter!!.setData(itemList)

    }

    class BannerViewHolder<VB : ViewDataBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)

}