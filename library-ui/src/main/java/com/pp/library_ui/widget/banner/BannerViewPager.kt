package com.pp.library_ui.widget.banner

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.pp.library_ui.R

class BannerViewPager<T> : RelativeLayout {

    private var mContext: Context = context
    private lateinit var mViewPager: ViewPager2

    private var mBannerPagerAdapter: BaseBannerAdapter<T, *>? = null
    private var onPageChangeCallback: OnPageChangeCallback? = null
    private var mCompositePageTransformer: CompositePageTransformer? = null
    private var mMarginPageTransformer: MarginPageTransformer? = null
    private var mOnPageClickListener: BaseBannerAdapter.OnPagerClickListener? = null

    private var lastPosition = 0
    private var listSize = 0

    private var isLooper = true

    private var pageMargin = 0

    //    private var revealWidth = -1
    private var revealWidth = 40

    private var offscreenPageLimit = 3

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
        mCompositePageTransformer = CompositePageTransformer()
        mViewPager.setPageTransformer(mCompositePageTransformer)
    }

    private fun initView() {
        inflate(context, R.layout.banner_vp_layout, this)
        mViewPager = findViewById(R.id.vp2)

    }

    private val mOnPagerChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            val realPosition: Int = mBannerPagerAdapter!!.getRealPosition(position)
            onPageChangeCallback?.onPageScrolled(realPosition, positionOffset, positionOffsetPixels)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            onPageChangeCallback?.onPageScrollStateChanged(state)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val realPosition: Int = mBannerPagerAdapter!!.getRealPosition(position)
            onPageChangeCallback?.onPageSelected(realPosition)
        }
    }

    fun create(data: List<T>) {
        if (mBannerPagerAdapter == null) {
            throw NullPointerException("You must set adapter for BannerViewPager")
        }
        listSize = data.size
        mBannerPagerAdapter!!.setData(data)
        initBannerData(data)
    }

    private fun initBannerData(list: List<T>) {
        if (list.isNotEmpty()) {
            setupViewPager(list)
        }
    }

    private fun setupViewPager(list: List<T>) {
        if (mBannerPagerAdapter == null) {
            throw NullPointerException("You must set adapter for BannerViewPager")
        }

        if (revealWidth != -1) {
            val recyclerView = mViewPager.getChildAt(0) as RecyclerView
            recyclerView.setPadding(pageMargin + revealWidth, 0, pageMargin + revealWidth, 0)
            recyclerView.clipToPadding = false
        }
        mBannerPagerAdapter!!.isCanLoop = isLooper
        mBannerPagerAdapter!!.pagerClickListener = mOnPageClickListener
        mViewPager.adapter = mBannerPagerAdapter
        resetCurrentItem()

        mViewPager.unregisterOnPageChangeCallback(mOnPagerChangeCallback)
        mViewPager.registerOnPageChangeCallback(mOnPagerChangeCallback)
        mViewPager.offscreenPageLimit = offscreenPageLimit
    }

    fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        if (isLooper) {
            val currentItem = mViewPager.currentItem
            val realPosition: Int = mBannerPagerAdapter!!.getRealPosition(currentItem)
            if (currentItem != item) {
                if (item == 0 && realPosition == listSize - 1) {
                    mViewPager.setCurrentItem(currentItem + 1, smoothScroll)
                } else if (realPosition == 0 && item == listSize - 1) {
                    mViewPager.setCurrentItem(currentItem - 1, smoothScroll)
                } else {
                    mViewPager.setCurrentItem(currentItem + (item - realPosition), smoothScroll)
                }
            }
        } else {
            mViewPager.setCurrentItem(item, smoothScroll)
        }
    }

    fun refreshData(list: List<T>) {
        if (mBannerPagerAdapter != null && list.isNotEmpty()) {
            listSize = list.size
            mBannerPagerAdapter!!.setData(list)
            mBannerPagerAdapter!!.notifyDataSetChanged()
            resetCurrentItem()

        }
    }

    private fun resetCurrentItem() {
        if (listSize > 1 && isLooper) {
            lastPosition = Int.MAX_VALUE / 2 - ((Int.MAX_VALUE / 2))
            mViewPager.setCurrentItem(lastPosition, false)
        } else {
            mViewPager.setCurrentItem(0, false)
        }
    }

    fun setAdapter(adapter: BaseBannerAdapter<T, *>) {
        mBannerPagerAdapter = adapter

        val data = mBannerPagerAdapter!!.getData()
        listSize = data.size

        initBannerData(data)
    }

    fun setOnPageChangedCallback(callback: OnPageChangeCallback) {
        onPageChangeCallback = callback
    }
}