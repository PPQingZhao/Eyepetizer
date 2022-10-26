package com.pp.module_home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_ui.databinding.ItemVideoCardBinding

class DailyPagingDataAdapter2 :
    BindingPagingDataAdapter<ViewDataBinding, MetroLargeVideoCardItemViewModel, MetroLargeVideoCardItemViewModel>(
        DIFF_CALLBACK
    ) {

    companion object {
        const val TAG = "DailyAdapter"

        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MetroLargeVideoCardItemViewModel>() {

                override fun areItemsTheSame(
                    oldItem: MetroLargeVideoCardItemViewModel,
                    newItem: MetroLargeVideoCardItemViewModel
                ) =
                    oldItem.metro?.metroId == newItem.metro?.metroId

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: MetroLargeVideoCardItemViewModel,
                    newItem: MetroLargeVideoCardItemViewModel
                ) =
                    oldItem == newItem
            }
    }


    override fun createViewModel(
        binding: ViewDataBinding,
        item: MetroLargeVideoCardItemViewModel?,
        cacheItemViewModel: MetroLargeVideoCardItemViewModel?
    ): MetroLargeVideoCardItemViewModel {
        return cacheItemViewModel ?: item ?: MetroLargeVideoCardItemViewModel(null)
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemVideoCardBinding.inflate(layoutInflater, parent, false)
    }

}