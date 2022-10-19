package com.pp.module_community.model

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.adapter.BindingHolder
import com.pp.library_ui.databinding.ItemImageVideoBinding
import com.pp.library_ui.model.ImageVideoItemViewModel
import com.pp.module_community.api.bean.CommunityRecBean
import com.pp.module_community.databinding.ItemSquareContentBinding

open class RecSquareItemViewModel(val item: CommunityRecBean.Item?) {

    val itemList = mutableListOf<CommunityRecBean.ItemX>()

    var adapter: RecyclerView.Adapter<BindingHolder<ItemSquareContentBinding>>? = null
    init {
        item?.data?.itemList?.let {
            itemList.addAll(it)
        }
        adapter = ItemAdapter().apply {
            setDataList(itemList)
        }
    }

    class ItemAdapter :
        BindingAdapter<ItemSquareContentBinding, SquareContentItemViewModel, CommunityRecBean.ItemX>() {
        override fun createViewModel(
            binding: ItemSquareContentBinding,
            item: CommunityRecBean.ItemX?,
            cacheItemViewModel: SquareContentItemViewModel?
        ): SquareContentItemViewModel {
            return cacheItemViewModel ?: SquareContentItemViewModel(item?.data?.title, item?.data?.subTitle, item?.data?.bgPicture)
        }

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemSquareContentBinding {
            return ItemSquareContentBinding.inflate(layoutInflater, parent, false)
        }
    }

}