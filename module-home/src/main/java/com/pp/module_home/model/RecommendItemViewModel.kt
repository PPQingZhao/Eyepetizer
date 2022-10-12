package com.pp.module_home.model

import com.pp.module_home.api.bean.RecommendBean

class RecommendItemViewModel(private val item: RecommendBean.Item?) {
    var content = "recommend==>"

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