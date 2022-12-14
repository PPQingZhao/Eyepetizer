package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.pp.library_network.utils.TrackingDataAdapter

data class Card(
    @SerializedName("allow_insert_above")
    val allowInsertAbove: Int,
    @SerializedName("allow_insert_card")
    val allowInsertCard: Int,
    @SerializedName("card_data")
    val cardData: CardData,
    @SerializedName("card_id")
    val cardId: Int,
    @SerializedName("card_unique_id")
    val cardUniqueId: String,
    @SerializedName("create_source")
    val createSource: String,
    @SerializedName("interaction")
    val interaction: Interaction,
    @SerializedName("special_pos")
    val specialPos: String,
    @SerializedName("style")
    val style: Style,
    @JsonAdapter(TrackingDataAdapter::class)
    @SerializedName("tracking_data")
    val trackingData: TrackingData?,
    @SerializedName("type")
    val type: String
) {
    data class CardData(
        @SerializedName("body")
        val body: Body,
        @SerializedName("footer")
        val footer: Footer,
        @SerializedName("header")
        val header: Header
    ) {
        data class Body(
            @SerializedName("api_request")
            val apiRequest: ApiRequest?,
            @SerializedName("metro_list")
            val metroList: List<Metro>?
        )

        data class Footer(
            @SerializedName("center")
            val center: List<Any>,
            @SerializedName("left")
            val left: List<Any>,
            @SerializedName("right")
            val right: List<Any>,
            @SerializedName("style")
            val style: Style
        )

        data class Header(
            @SerializedName("center")
            val center: List<CardHeadData>,
            @SerializedName("left")
            val left: List<CardHeadData>,
            @SerializedName("right")
            val right: List<CardHeadData>,
            @SerializedName("style")
            val style: Style
        ) {
            data class CardHeadData(
                @SerializedName("alias_name")
                val aliasName: String,
                @SerializedName("allow_insert_above")
                val allowInsertAbove: Int,
                @SerializedName("metro_data")
                val metroData: MetroDataBean,
                @SerializedName("metro_id")
                val metroId: Int,
                @SerializedName("metro_unique_id")
                val metroUniqueId: String,
                @SerializedName("style")
                val style: Style,
                @SerializedName("tracking_data")
                val trackingData: TrackingData,
                @SerializedName("tracking_params")
                val trackingParams: Metro.TrackingParams,
                @SerializedName("type")
                val type: String
            )
        }
    }

    data class Interaction(
        @SerializedName("scroll")
        val scroll: String
    )

}

data class Modal(
    @SerializedName("data")
    val `data`: Data,
    @JsonAdapter(TrackingDataAdapter::class)
    @SerializedName("tracking_data")
    val trackingData: TrackingData
) {
    data class Data(
        @SerializedName("bg_image")
        val bgImage: BgImage,
        @SerializedName("body")
        val body: Body,
        @SerializedName("enable_cancel")
        val enableCancel: Boolean,
        @SerializedName("modal_id")
        val modalId: String,
        @SerializedName("redirect_url")
        val redirectUrl: String,
        @SerializedName("show_duration")
        val showDuration: Int,
        @SerializedName("show_rule")
        val showRule: String
    ) {
        data class BgImage(
            @SerializedName("img_info")
            val imgInfo: ImgInfo,
            @SerializedName("url")
            val url: String
        ) {
            data class ImgInfo(
                @SerializedName("height")
                val height: Int,
                @SerializedName("scale")
                val scale: Double,
                @SerializedName("width")
                val width: Int
            )
        }

        data class Body(
            @SerializedName("corner_radius")
            val cornerRadius: Int,
            @SerializedName("scale")
            val scale: Double,
            @SerializedName("url")
            val url: String,
            @SerializedName("width_ratio")
            val widthRatio: Double
        )
    }

}

data class PageInfo(
    @SerializedName("base_link")
    val baseLink: String,
    @SerializedName("enable_share")
    val enableShare: Boolean,
    @SerializedName("page_id")
    val pageId: Int,
    @SerializedName("page_label")
    val pageLabel: String,
    @SerializedName("show_the_end")
    val showTheEnd: Boolean,
//        @JsonAdapter(StyleTypeAdapter::class)
//        @SerializedName("style")
//        val style: List<Style?>?,
    @SerializedName("title")
    val title: String,
    @JsonAdapter(TrackingDataAdapter::class)
    @SerializedName("tracking_data")
    val trackingData: TrackingData
)