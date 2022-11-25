package com.pp.module_video_details.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.DefaultLoadMoreStateAdapter
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.module_video_details.databinding.FragmentIntroductionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class IntroductionFragment(details: MetroDataBean?) :
    ThemeFragment<FragmentIntroductionBinding, IntroductionViewModel>() {
    override val mBinding by lazy { FragmentIntroductionBinding.inflate(layoutInflater) }
    override fun getModelClazz(): Class<IntroductionViewModel> {
        return IntroductionViewModel::class.java
    }

    private val modelFactory: ViewModelProvider.Factory

    init {
        modelFactory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    IntroductionViewModel::class.java
                    -> IntroductionViewModel(details, activity?.application!!) as T

                    else -> super@IntroductionFragment.getModelFactory().create(modelClass)
                }
            }

        }
    }

    override fun getModelFactory(): ViewModelProvider.Factory = modelFactory

    constructor() : this(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()

    }

    override fun onFirstResume() {
        lifecycleScope.launch(Dispatchers.IO) {
            mViewModel.getRelatedRecommend().collect {
                mAdapter.submitData(lifecycle,it)
            }
        }
    }

    val mAdapter = MetroPagingDataAdapterType.largeVideoCardPagingDataAdapter

    private fun initRecyclerView() {
        mBinding.introductionRecyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.introductionRecyclerview.adapter =
            mAdapter.withLoadStateFooter(DefaultLoadMoreStateAdapter(
                lifecycle = lifecycle,
            ) {
                mAdapter.retry()
            })

    }
}