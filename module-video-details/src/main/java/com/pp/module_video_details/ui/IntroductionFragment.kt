package com.pp.module_video_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pp.library_base.adapter.attachRecyclerView
import com.pp.library_base.adapter.attachStateView
import com.pp.library_base.adapter.onErrorListener
import com.pp.library_base.base.ThemeFragment
import com.pp.library_common.adapter.MetroPagingDataAdapterType
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_ui.utils.StateView
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

    override fun onFirstResume() {
        mAdapter.attachRecyclerView(viewLifecycleOwner.lifecycle,mBinding.introductionRecyclerview)
        lifecycleScope.launch {
            mAdapter.attachStateView(
                StateView.DefaultBuilder(lifecycle, mBinding.introductionRecyclerview)
                    .setOnErrorClickListener(mAdapter.onErrorListener())
                    .setThemeViewModel(requireTheme())
                    .build()
            )
        }
        lifecycleScope.launch(Dispatchers.IO) {
            mViewModel.getRelatedRecommend().collect {
                mAdapter.submitData(lifecycle, it)
            }
        }
    }

    private val mAdapter = MetroPagingDataAdapterType.largeVideoCard2PagingDataAdapter()

}