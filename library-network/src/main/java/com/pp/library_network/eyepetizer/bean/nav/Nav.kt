package com.pp.library_network.eyepetizer.bean.nav


import com.google.gson.annotations.SerializedName
import com.pp.library_network.eyepetizer.bean.ApiRequest

data class Nav(
    @SerializedName("api_request")
    val apiRequest: ApiRequest?,
    @SerializedName("child_nav")
    val childNav: ChildNav,
    @SerializedName("default_display")
    val defaultDisplay: Boolean,
    @SerializedName("enable_preload")
    val enablePreload: Boolean,
    @SerializedName("force_refresh")
    val forceRefresh: Boolean,
    @SerializedName("is_recommend")
    val isRecommend: Boolean,
    @SerializedName("page_label")
    val pageLabel: String,
    @SerializedName("page_type")
    val pageType: String,
    @SerializedName("page_url")
    val pageUrl: String,
    @SerializedName("page_url_parameter")
    val pageUrlParameter: String,
    @SerializedName("preload_duration")
    val preloadDuration: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracking_data")
    val trackingData: TrackingData,
    @SerializedName("url")
    val url: String
)