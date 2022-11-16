package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName
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