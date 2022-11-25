package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.pp.library_network.utils.TrackingDataAdapter

data class Metro(
    @SerializedName("alias_name")
    val aliasName: String = "",
    @SerializedName("allow_insert_above")
    val allowInsertAbove: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("metro_data")
    val metroData: MetroDataBean? = null,
    @SerializedName("metro_id")
    val metroId: Int = 0,
    @SerializedName("metro_unique_id")
    val metroUniqueId: String = "",
    @SerializedName("style")
    val style: Style = Style(),
    @JsonAdapter(TrackingDataAdapter::class)
    @SerializedName("tracking_data")
    val trackingData: TrackingData? = null,
    @SerializedName("tracking_params")
    val trackingParams: TrackingParams? = null,
    @SerializedName("type")
    val type: String = ""
) {

    class TrackingParams() {
        @SerializedName("data_source")
        var dataSource: List<DataSource>? = null

        @SerializedName("is_ad")
        var isAd: Int? = null

        class DataSource() {
            @SerializedName("label")
            var label: String? = ""

            @SerializedName("params")
            var params: Params? = null

            class Params() {
                @SerializedName("data_id")
                var dataId: Int = 0

                @SerializedName("num")
                var num: Int = 0
            }
        }
    }
}

