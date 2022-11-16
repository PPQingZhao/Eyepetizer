package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.pp.library_network.utils.StyleTypeAdapter
import com.pp.library_network.utils.TrackingDataAdapter


data class PageDataBean(
    @SerializedName("card_list")
    val cardList: List<Card>,
    @SerializedName("float_entrance")
    val floatEntrance: List<Any>,
    @SerializedName("modal_list")
    val modalList: List<Modal>,
    @SerializedName("page_info")
    val pageInfo: PageInfo,
    @SerializedName("grant_type")
    val grant_type: String?,
    @SerializedName("server_timestamp")
    val server_timestamp: Long?,
) {
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
            ) {
                data class ApiRequest(
                    @SerializedName("params")
                    val params: Params,
                    @SerializedName("url")
                    val url: String
                ) {
                    data class Params(
                        @SerializedName("card")
                        val card: String,
                        @SerializedName("card_index")
                        val cardIndex: Int,
                        @SerializedName("data_source")
                        val dataSource: String,
                        @SerializedName("last_item_id")
                        val lastItemId: Int,
                        @SerializedName("material")
                        val material: String,
                        @SerializedName("material_index")
                        val materialIndex: Int,
                        @SerializedName("material_relative_index")
                        val materialRelativeIndex: Int,
                        @SerializedName("page_label")
                        val pageLabel: String,
                        @SerializedName("page_params")
                        val pageParams: String
                    )
                }

                data class Metro(
                    @SerializedName("alias_name")
                    val aliasName: String?,
                    @SerializedName("allow_insert_above")
                    val allowInsertAbove: Int?,
                    @SerializedName("link")
                    val link: String,
                    @SerializedName("metro_data")
                    val metroData: MetroDataBean,
                    @SerializedName("metro_id")
                    val metroId: Int,
                    @SerializedName("metro_unique_id")
                    val metroUniqueId: String,
                    @SerializedName("style")
                    val style: Style,
                    @JsonAdapter(TrackingDataAdapter::class)
                    @SerializedName("tracking_data")
                    val trackingData: TrackingData,
                    @SerializedName("tracking_params")
                    val trackingParams: TrackingParams,
                    @SerializedName("type")
                    val type: String
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
            }

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
                val center: List<Any>,
                @SerializedName("left")
                val left: List<Any>,
                @SerializedName("right")
                val right: List<Any>,
                @SerializedName("style")
                val style: Style
            )
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
        @JsonAdapter(StyleTypeAdapter::class)
        @SerializedName("style")
        val style: Style?,
        @SerializedName("title")
        val title: String,
        @JsonAdapter(TrackingDataAdapter::class)
        @SerializedName("tracking_data")
        val trackingData: TrackingData
    )
}
