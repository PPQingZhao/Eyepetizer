package com.pp.module_video_details.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.base.ThemeActivity
import com.pp.library_common.data.DetailsData
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.utils.DefaultAnimatorListener
import com.pp.library_ui.utils.alpha
import com.pp.library_ui.utils.heightAnimator
import com.pp.library_ui.utils.translationY
import com.pp.module_video_details.R
import com.pp.module_video_details.databinding.ActivitySmallVideoDetailsBinding
import kotlinx.coroutines.launch

@Route(path = RouterPath.ItemDetails.activity_small_video_details)
class SmallVideoDetailsActivity :
    ThemeActivity<ActivitySmallVideoDetailsBinding, SmallVideoDetailsViewModel>() {
    override val mBinding by lazy {
        ActivitySmallVideoDetailsBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<SmallVideoDetailsViewModel> {
        return SmallVideoDetailsViewModel::class.java
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        mBinding.root.setOnApplyWindowInsetsListener { v, insets ->
            mBinding.video.dispatchApplyWindowInsets(insets)
            mBinding.titleBar.dispatchApplyWindowInsets(insets)
        }
        initView()
        initComment()

        lifecycleScope.launch {
            val video = mViewModel.getVideoDetails(resourceId, resourceType)
            showDetails()
            startPlay(video)
        }

    }

    private fun startPlay(playUrl: String?) {
        mBinding.video.isShowCover = false
        val player = mBinding.video.startPlayWhenReady(playUrl)
        mBinding.playerController.player = player
    }

    private fun showDetails() {
        mBinding.includeDetail.parent.visibility = View.VISIBLE
        mBinding.playerController.visibility = View.VISIBLE
        mBinding.includeDetail.parent.alpha(0f, 0.3f, 0.6f, 1.0f)
        mBinding.playerController.alpha(0f, 0.3f, 0.6f, 1.0f)
    }


    private fun initView() {
        mBinding.includeDetail.tvContent.movementMethod = ScrollingMovementMethod()
        mBinding.llComment.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
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

        mBinding.includeDetail.tvComment.setOnClickListener {
            showComment()
        }

        mBinding.ivCloseComment.setOnClickListener {
            dismissComment()
        }
    }

    private fun startScaleVideoHeightAnimator1() {
        val maxHeight = resources.displayMetrics.heightPixels
        val minHeight =
            resources.displayMetrics.heightPixels - mBinding.llComment.height
        mBinding.video.heightAnimator(
            maxHeight,
            minHeight,
            listener = DefaultAnimatorListener(onAnimationStart = {
                mBinding.titleBar.visibility = View.GONE
            }, onAnimationEnd = {
                mBinding.video.layoutParams.height = minHeight
            })
        )
    }

    private fun startScaleVideoHeightAnimator2() {
        val maxHeight = resources.displayMetrics.heightPixels
        val minHeight =
            resources.displayMetrics.heightPixels - mBinding.llComment.height
        mBinding.video.heightAnimator(
            minHeight,
            maxHeight,
            listener = DefaultAnimatorListener(onAnimationEnd = {
                mBinding.titleBar.visibility = View.VISIBLE
                mBinding.video.layoutParams.height = maxHeight
            })
        )
    }

    private fun showComment() {
        startScaleVideoHeightAnimator1()
        mBinding.llComment.translationY(true)
    }

    private fun dismissComment() {
        startScaleVideoHeightAnimator2()
        mBinding.llComment.translationY(false)
    }

    private fun initComment() {
        val fragment = ARouter.getInstance().build(RouterPath.Comments.fragment_comments)
            .withLong("resourceId", resourceId ?: 0)
            .withString("resourceType", resourceType)
            .navigation()
                as Fragment

        supportFragmentManager.beginTransaction().add(R.id.fl_comment, fragment).commit()
    }

    private fun startPlay(video: MetroDataBean.Video?) {
        video?.apply {
//            mBinding.video.setCover(cover.url)
            startPlay(playUrl)
        }
    }

    fun onBack(view: View) {
        finish()
    }

    override fun onBackPressed() {
        if (mBinding.llComment.visibility == View.VISIBLE) {
            dismissComment()
            return
        }
        super.onBackPressed()
    }

}