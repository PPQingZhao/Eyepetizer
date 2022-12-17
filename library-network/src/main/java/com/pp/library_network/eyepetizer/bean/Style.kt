package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName

data class Style(
    @SerializedName("across_column")
    val acrossColumn: Boolean? = false,
    @SerializedName("background")
    val background: Background? = null,
    @SerializedName("banner_padding")
    val bannerPadding: Int? = 0,
    @SerializedName("padding")
    val padding: Padding? = null,
    @SerializedName("separator_line")
    val separatorLine: SeparatorLine? = null,
    @SerializedName("tpl_label")
    val tplLabel: String = ""
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
            val bottom: Float,
            @SerializedName("left")
            val left: Float,
            @SerializedName("right")
            val right: Float,
            @SerializedName("top")
            val top: Float
        )
    }
}