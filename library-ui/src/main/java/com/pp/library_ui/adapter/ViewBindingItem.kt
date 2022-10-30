package com.pp.library_ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class ViewBindingItem<out Data : Any?> {
    companion object {
        // 无效 item type
        const val ITEM_TYPE_INVALID = -1

    }

    val adapterBindingHelper: AdapterBindingHelper<ViewDataBinding, Any, @UnsafeVariance Data> =
        object : AdapterBindingHelper<ViewDataBinding, Any, Data>() {
            override fun createViewModel(
                binding: ViewDataBinding,
                item: @UnsafeVariance Data?,
                cacheItemViewModel: Any?
            ): Any? {
                return this@ViewBindingItem.onCreateViewModel(binding, item,cacheItemViewModel)
            }

            override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
                return this@ViewBindingItem.onCreateViewDataBinding(parent)
            }

        }

    /**
     * 创建 view data binding
     */
    abstract fun onCreateViewDataBinding(parent: ViewGroup): ViewDataBinding

    /**
     * 创建 item view model
     */
    abstract fun onCreateViewModel(
        binding: ViewDataBinding,
        item: @UnsafeVariance Data?,
        cacheItemViewModel: Any?
    ): Any?


    /**
     * 正确的 item type
     */
    abstract fun getType(): Int

    /**
     * 数据是否正确
     */
    abstract fun isRight(item: @UnsafeVariance Data?): Boolean

    /**
     * 获取 item type
     */
    fun getItemType(position: Int, item: @UnsafeVariance Data?): Int {
        return try {
            if (isRight(item)) getType() else ITEM_TYPE_INVALID
        } catch (e: java.lang.ClassCastException) {
//            e.printStackTrace()
            ITEM_TYPE_INVALID
        }
    }

}