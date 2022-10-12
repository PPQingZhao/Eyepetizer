package com.pp.module_home.model

import com.pp.module_home.api.bean.FeedBean


class DailyItemViewModel(private val item: FeedBean.Item?) {
    var content = "daily==>"

    init {
        content = """
            $content
                         item type: ${item?.type ?: "null"}
                    item data type: ${item?.data?.dataType ?: "null"}
                      content type: ${item?.data?.content?.type ?: "null"}
                 content data type: ${item?.data?.content?.data?.type ?: "null"}
        """.trimIndent()
    }
}