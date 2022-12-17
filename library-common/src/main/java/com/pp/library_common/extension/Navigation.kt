package com.pp.library_common.extension

import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_common.data.DetailsData
import com.pp.library_network.eyepetizer.bean.ApiRequest
import com.pp.library_router_service.services.RouterPath

fun intentToVideoDetails(resourceId: Long, resourceType: String?) {
    ARouter.getInstance()
        .build(RouterPath.ItemDetails.activity_small_video_details)
        .withLong("resourceId", resourceId)
        .withString("resourceType", resourceType)
        .navigation()
}

fun intentToVideoDetails(detailsData: DetailsData) {
    ARouter.getInstance()
        .build(RouterPath.ItemDetails.activity_small_video_details)
        .withSerializable("detailsData", detailsData)
        .navigation()
}

fun intentToImageDetails(detailsData: DetailsData) {
    ARouter.getInstance()
        .build(RouterPath.ItemDetails.activity_image_details)
        .withSerializable("detailsData", detailsData)
        .navigation()
}

fun intentToImageDetails(resourceId: Long, resourceType: String?) {
    ARouter.getInstance()
        .build(RouterPath.ItemDetails.activity_image_details)
        .withLong("resourceId", resourceId)
        .withString("resourceType", resourceType)
        .navigation()
}

fun intentToTopicSquare(resourceType: String?) {
    ARouter.getInstance()
        .build(RouterPath.Discovery.activity_topic_square)
        .withString("type", resourceType)
        .navigation()
}

fun intentToTopicList(apiRequest: ApiRequest) {
    ARouter.getInstance()
        .build(RouterPath.Discovery.activity_topic_list)
        .withSerializable("apiRequest", apiRequest)
        .navigation()
}

fun intentToTopic(url: String?) {
    ARouter.getInstance()
        .build(RouterPath.Discovery.activity_topic)
        .withString("url", url)
        .navigation()
}