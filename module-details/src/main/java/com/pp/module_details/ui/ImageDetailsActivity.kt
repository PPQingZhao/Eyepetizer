package com.pp.module_details.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.base.ThemeActivity
import com.pp.library_network.eyepetizer.bean.ImageBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.BindingAdapter
import com.pp.library_ui.utils.*
import com.pp.module_details.R
import com.pp.module_details.databinding.ActivityImageDetailsBinding
import com.pp.module_details.databinding.ItemDetailImageBinding
import kotlinx.coroutines.launch

@Route(path = RouterPath.ItemDetails.activity_image_details)
class ImageDetailsActivity : ThemeActivity<ActivityImageDetailsBinding, ImageDetailsViewModel>() {
    override val mBinding by lazy {
        ActivityImageDetailsBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<ImageDetailsViewModel> {
        return ImageDetailsViewModel::class.java
    }

    @JvmField
    @Autowired(name = "resourceId")
    var resourceId: Long? = 0

    @JvmField
    @Autowired(name = "resourceType")
    var resourceType: String? = ""

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        requireLightStatusBar(false)
    }

    private fun initComment() {
        val fragment = ARouter.getInstance().build(RouterPath.Comments.fragment_comments)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
                as Fragment

        supportFragmentManager.beginTransaction().add(R.id.fl_comment, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        initView()
        initViewPager()
        initComment()
        lifecycleScope.launch {
            val images = mViewModel.getImageDetails(resourceId, resourceType)
            mAdapter.setDataList(images ?: mutableListOf())
            mBinding.tvIndicator.post {
                mBinding.tvIndicator.visibility =
                    if (mAdapter.itemCount > 1) View.VISIBLE else View.GONE
            }
        }

    }

    private fun initView() {
        mBinding.include.tvContent.movementMethod = ScrollingMovementMethod()
        mBinding.llComment.addOnLayoutChangeListener(object : OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int,
            ) {
                mBinding.llComment.visibility = View.GONE
                mBinding.llComment.removeOnLayoutChangeListener(this)
            }
        })
        mBinding.llComment.visibility = View.VISIBLE
        mBinding.include.tvComment.setOnClickListener {
            mBinding.llComment.startTranslationY(true)
        }

        mBinding.ivCloseComment.setOnClickListener {
            mBinding.llComment.startTranslationY(false)
        }
    }

    private val mAdapter = ImageAdapter()
    private fun initViewPager() {
        mBinding.viewpager2.adapter = mAdapter
        mBinding.viewpager2.offscreenPageLimit = 2
        mBinding.viewpager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                mBinding.tvIndicator.text = "${position + 1}/${mAdapter.itemCount}"
            }
        })
    }

    fun onBack(view: View) {
        finish()
    }

    private class ImageAdapter : BindingAdapter<ItemDetailImageBinding, String, ImageBean>() {
        override fun createViewModel(
            binding: ItemDetailImageBinding,
            item: ImageBean?,
            cacheItemViewModel: String?,
        ): String? {
            return item?.cover?.url
        }

        override fun createBinding(parent: ViewGroup, viewType: Int): ItemDetailImageBinding {
            return ItemDetailImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        }

    }

}