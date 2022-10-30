package com.pp.module_community.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_ui.widget.banner.BaseBannerAdapter
import com.pp.module_community.databinding.ItemBannerContentBinding
import com.pp.module_community.respository.SquareType

class BannerListViewModel(
    val card: PageDataBean.Card,
    val metroList: List<PageDataBean.Card.CardData.Body.Metro>
) : MultiItemEntity {
    override val itemType: Int
        get() = SquareType.TYPE_BANNER_LIST

    val itemList = mutableListOf<BannerContentItemViewModel>()
    val index = ObservableInt()

    var bannerAdapter: BaseBannerAdapter<BannerContentItemViewModel, ViewHolder<ItemBannerContentBinding>>? = null

    var onPageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                index.set(position + 1)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        }

    init {

        metroList.forEach { metro ->
            itemList.add(BannerContentItemViewModel(metro.metroData.cover.url))
        }

        bannerAdapter = object: BaseBannerAdapter<BannerContentItemViewModel, ViewHolder<ItemBannerContentBinding>>(){


            override fun onBind(
                holder: ViewHolder<ItemBannerContentBinding>,
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
            ): ViewHolder<ItemBannerContentBinding> {
                val binding = ItemBannerContentBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }

        }

        bannerAdapter!!.setData(itemList)

    }

    class ViewHolder<VB : ViewDataBinding>(val binding: VB): RecyclerView.ViewHolder(binding.root)
}