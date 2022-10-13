package com.pp.module_community.model

import com.pp.module_community.api.bean.CommunityRecBean

class RecItemViewModel(val item: CommunityRecBean.Item?) {

    var content = "Recommend==>"

    init {
        content = """
            $content
                          itemType: ${item?.type ?: "null"}
                    item data type: ${item?.data?.dataType ?: "null"}
                      content type: ${item?.data?.content?.type ?: "null"}
                 content data type: ${item?.data?.content?.data?.dataType ?: "null"}       
            
        """.trimIndent()
    }
}