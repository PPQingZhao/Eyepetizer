package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName


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
                    val metroData: MetroData,
                    @SerializedName("metro_id")
                    val metroId: Int,
                    @SerializedName("metro_unique_id")
                    val metroUniqueId: String,
                    @SerializedName("style")
                    val style: Style,
                    @SerializedName("tracking_data")
                    val trackingData: TrackingData,
                    @SerializedName("tracking_params")
                    val trackingParams: TrackingParams,
                    @SerializedName("type")
                    val type: String
                ) {
                    data class MetroData(
                        @SerializedName("author")
                        val author: Author?,
                        @SerializedName("cover")
                        val cover: Cover,
                        @SerializedName("crop_area")
                        val cropArea: CropArea?,
                        @SerializedName("duration")
                        val duration: Duration?,
                        @SerializedName("footer")
                        val footer: Footer?,
                        @SerializedName("hot_value")
                        val hotValue: Int?,
                        @SerializedName("image_id")
                        val imageId: Int?,
                        @SerializedName("play_ctrl")
                        val playCtrl: PlayCtrl?,
                        @SerializedName("play_url")
                        val playUrl: String?,
                        @SerializedName("preview_url")
                        val previewUrl: String?,
                        @SerializedName("recommend_level")
                        val recommendLevel: String?,
                        @SerializedName("resource_id")
                        val resourceId: Int,
                        @SerializedName("resource_type")
                        val resourceType: String,
                        @SerializedName("tags")
                        val tags: List<Tag>?,
                        @SerializedName("title")
                        val title: String?,
                        @SerializedName("video_id")
                        val videoId: String?
                    ) {
                        data class Author(
                            @SerializedName("avatar")
                            val avatar: Avatar,
                            @SerializedName("description")
                            val description: String,
                            @SerializedName("followed")
                            val followed: Boolean,
                            @SerializedName("link")
                            val link: String,
                            @SerializedName("nick")
                            val nick: String,
                            @SerializedName("type")
                            val type: String,
                            @SerializedName("uid")
                            val uid: Int
                        ) {
                            data class Avatar(
                                @SerializedName("img_info")
                                val imgInfo: ImgInfo,
                                @SerializedName("shape")
                                val shape: String,
                                @SerializedName("url")
                                val url: String
                            ) {
                                data class ImgInfo(
                                    @SerializedName("height")
                                    val height: Int,
                                    @SerializedName("scale")
                                    val scale: Int,
                                    @SerializedName("width")
                                    val width: Int
                                )
                            }
                        }

                        data class Cover(
                            @SerializedName("img_info")
                            val imgInfo: ImgInfo,
                            @SerializedName("url")
                            val url: String
                        ) {
                            data class ImgInfo(
                                @SerializedName("height")
                                val height: Double,
                                @SerializedName("scale")
                                val scale: Double,
                                @SerializedName("width")
                                val width: Int
                            )
                        }

                        data class CropArea(
                            @SerializedName("height")
                            val height: Float,
                            @SerializedName("origin_height")
                            val originHeight: Float,
                            @SerializedName("origin_width")
                            val originWidth: Float,
                            @SerializedName("width")
                            val width: Float,
                            @SerializedName("x")
                            val x: Float,
                            @SerializedName("y")
                            val y: Float
                        )

                        data class Duration(
                            @SerializedName("text")
                            val text: String,
                            @SerializedName("value")
                            val value: Int
                        )

                        data class Footer(
                            @SerializedName("left")
                            val left: Left,
                            @SerializedName("right")
                            val right: Right
                        ) {
                            data class Left(
                                @SerializedName("link")
                                val link: String,
                                @SerializedName("text")
                                val text: String
                            )

                            data class Right(
                                @SerializedName("text")
                                val text: String
                            )
                        }

                        data class PlayCtrl(
                            @SerializedName("autoplay")
                            val autoplay: Boolean,
                            @SerializedName("autoplay_times")
                            val autoplayTimes: Int,
                            @SerializedName("need_cellular")
                            val needCellular: Boolean,
                            @SerializedName("need_wifi")
                            val needWifi: Boolean,
                            @SerializedName("need_wifi_preload")
                            val needWifiPreload: Boolean
                        )

                        data class Tag(
                            @SerializedName("id")
                            val id: Int,
                            @SerializedName("link")
                            val link: String,
                            @SerializedName("title")
                            val title: String
                        )
                    }

                    data class Style(
                        @SerializedName("across_column")
                        val acrossColumn: Boolean?,
                        @SerializedName("background")
                        val background: Background,
                        @SerializedName("banner_padding")
                        val bannerPadding: Int?,
                        @SerializedName("padding")
                        val padding: Padding,
                        @SerializedName("separator_line")
                        val separatorLine: SeparatorLine,
                        @SerializedName("tpl_label")
                        val tplLabel: String
                    ) {
                        data class Background(
                            @SerializedName("color")
                            val color: String
                        )

                        data class Padding(
                            @SerializedName("bottom")
                            val bottom: Double,
                            @SerializedName("left")
                            val left: Double,
                            @SerializedName("right")
                            val right: Double,
                            @SerializedName("top")
                            val top: Double
                        )

                        data class SeparatorLine(
                            @SerializedName("bottom")
                            val bottom: Bottom,
                            @SerializedName("top")
                            val top: Top
                        ) {
                            data class Bottom(
                                @SerializedName("color")
                                val color: String,
                                @SerializedName("height")
                                val height: Double,
                                @SerializedName("margin")
                                val margin: Margin
                            )

                            data class Top(
                                @SerializedName("color")
                                val color: String,
                                @SerializedName("height")
                                val height: Double,
                                @SerializedName("margin")
                                val margin: Margin
                            )

                            data class Margin(
                                @SerializedName("bottom")
                                val bottom: Int,
                                @SerializedName("left")
                                val left: Int,
                                @SerializedName("right")
                                val right: Int,
                                @SerializedName("top")
                                val top: Int
                            )
                        }
                    }

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
            ) {
                data class Style(
                    @SerializedName("padding")
                    val padding: Padding
                ) {
                    data class Padding(
                        @SerializedName("bottom")
                        val bottom: Int,
                        @SerializedName("left")
                        val left: Int,
                        @SerializedName("right")
                        val right: Int,
                        @SerializedName("top")
                        val top: Int
                    )
                }
            }

            data class Header(
                @SerializedName("center")
                val center: List<Any>,
                @SerializedName("left")
                val left: List<Any>,
                @SerializedName("right")
                val right: List<Any>,
                @SerializedName("style")
                val style: Style
            ) {
                data class Style(
                    @SerializedName("padding")
                    val padding: Padding
                ) {
                    data class Padding(
                        @SerializedName("bottom")
                        val bottom: Int,
                        @SerializedName("left")
                        val left: Int,
                        @SerializedName("right")
                        val right: Int,
                        @SerializedName("top")
                        val top: Int
                    )
                }
            }
        }

        data class Interaction(
            @SerializedName("scroll")
            val scroll: String
        )

        data class Style(
            @SerializedName("background")
            val background: Background?,
            @SerializedName("padding")
            val padding: Padding,
            @SerializedName("separator_line")
            val separatorLine: SeparatorLine
        ) {
            data class Background(
                @SerializedName("color")
                val color: String
            )

            data class Padding(
                @SerializedName("bottom")
                val bottom: Double,
                @SerializedName("left")
                val left: Int,
                @SerializedName("right")
                val right: Int,
                @SerializedName("top")
                val top: Int
            )

            data class SeparatorLine(
                @SerializedName("bottom")
                val bottom: Bottom,
                @SerializedName("top")
                val top: Top
            ) {
                data class Bottom(
                    @SerializedName("color")
                    val color: String,
                    @SerializedName("height")
                    val height: Double,
                    @SerializedName("margin")
                    val margin: Margin
                )

                data class Top(
                    @SerializedName("color")
                    val color: String,
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("margin")
                    val margin: Margin
                )

                data class Margin(
                    @SerializedName("bottom")
                    val bottom: Int,
                    @SerializedName("left")
                    val left: Int,
                    @SerializedName("right")
                    val right: Int,
                    @SerializedName("top")
                    val top: Int
                )
            }
        }
    }

    data class Modal(
        @SerializedName("data")
        val `data`: Data,
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
        @SerializedName("style")
        val style: List<Any>,
        @SerializedName("title")
        val title: String,
        @SerializedName("tracking_data")
        val trackingData: TrackingData
    )
}
