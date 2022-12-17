package com.pp.library_common.data

data class DetailsData(
    val playUrl: String? = "",
    val content: String? = "",
    val icon: String? = "",
    val nickName: String? = "",
    val publish: String? = "",
    val tag: String? = "",
    val collectionCount: String? = "",
    val realCollectionCount: String? = "",
    val replyCount: String? = "",
) : java.io.Serializable