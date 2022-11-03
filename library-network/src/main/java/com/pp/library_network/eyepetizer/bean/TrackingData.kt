package com.pp.library_network.eyepetizer.bean

import com.google.gson.annotations.SerializedName
data class TrackingData(
    @SerializedName("click")
    val click: List<Click>,
    @SerializedName("show")
    val show: List<Show>
) {
    data class Click(
        @SerializedName("child")
        val child: List<Child>?,
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("sdk")
        val sdk: String
    ) {
        data class Child(
            @SerializedName("data")
            val `data`: Data,
            @SerializedName("type")
            val type: String
        ) {
            data class Data(
                @SerializedName("card_id")
                val cardId: Int,
                @SerializedName("card_index")
                val cardIndex: Int,
                @SerializedName("card_title")
                val cardTitle: String,
                @SerializedName("card_type")
                val cardType: String,
                @SerializedName("click_action")
                val clickAction: String,
                @SerializedName("click_action_url")
                val clickActionUrl: String,
                @SerializedName("click_name")
                val clickName: String,
                @SerializedName("dev_data_source")
                val devDataSource: String?,
                @SerializedName("dev_is_new_user")
                val devIsNewUser: Int?,
                @SerializedName("dev_recommend_recall_type")
                val devRecommendRecallType: String?,
                @SerializedName("dev_release_time")
                val devReleaseTime: String?,
                @SerializedName("element_content")
                val elementContent: String?,
                @SerializedName("element_id")
                val elementId: Int,
                @SerializedName("element_index")
                val elementIndex: Int,
                @SerializedName("element_label")
                val elementLabel: String,
                @SerializedName("element_title")
                val elementTitle: String,
                @SerializedName("element_type")
                val elementType: String,
                @SerializedName("metro_id")
                val metroId: Int,
                @SerializedName("relative_index")
                val relativeIndex: Int
            )

        }

        data class Data(
            @SerializedName("card_id")
            val cardId: Int?,
            @SerializedName("card_index")
            val cardIndex: Int?,
            @SerializedName("card_title")
            val cardTitle: String?,
            @SerializedName("card_type")
            val cardType: String?,
            @SerializedName("click_action")
            val clickAction: String?,
            @SerializedName("click_action_url")
            val clickActionUrl: String?,
            @SerializedName("click_name")
            val clickName: String?,
            @SerializedName("clickUrl")
            val clickUrl: String?,
            @SerializedName("dev_data_source")
            val devDataSource: String?,
            @SerializedName("dev_is_new_user")
            val devIsNewUser: Int?,
            @SerializedName("dev_recommend_recall_type")
            val devRecommendRecallType: String?,
            @SerializedName("dev_release_time")
            val devReleaseTime: String?,
            @SerializedName("element_content")
            val elementContent: String?,
            @SerializedName("element_id")
            val elementId: Int?,
            @SerializedName("element_index")
            val elementIndex: Int?,
            @SerializedName("element_label")
            val elementLabel: String?,
            @SerializedName("element_title")
            val elementTitle: String?,
            @SerializedName("element_type")
            val elementType: String?,
            @SerializedName("metro_id")
            val metroId: Int?,
            @SerializedName("monitorPositions")
            val monitorPositions: String?,
            @SerializedName("needExtraParams")
            val needExtraParams: List<Any>?,
            @SerializedName("organization")
            val organization: String?,
            @SerializedName("playUrl")
            val playUrl: String?,
            @SerializedName("relative_index")
            val relativeIndex: Int?,
            @SerializedName("viewUrl")
            val viewUrl: String?
        )
    }

    data class Show(
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("sdk")
        val sdk: String
    ) {
        data class Data(
            @SerializedName("card_id")
            val cardId: Int?,
            @SerializedName("card_index")
            val cardIndex: Int?,
            @SerializedName("card_title")
            val cardTitle: String?,
            @SerializedName("card_type")
            val cardType: String?,
            @SerializedName("clickUrl")
            val clickUrl: String?,
            @SerializedName("dev_data_source")
            val devDataSource: String?,
            @SerializedName("dev_is_new_user")
            val devIsNewUser: Int?,
            @SerializedName("dev_recommend_recall_type")
            val devRecommendRecallType: String?,
            @SerializedName("dev_release_time")
            val devReleaseTime: String?,
            @SerializedName("element_content")
            val elementContent: String?,
            @SerializedName("element_id")
            val elementId: Int?,
            @SerializedName("element_index")
            val elementIndex: Int?,
            @SerializedName("element_label")
            val elementLabel: String?,
            @SerializedName("element_title")
            val elementTitle: String?,
            @SerializedName("element_type")
            val elementType: String?,
            @SerializedName("metro_id")
            val metroId: Int?,
            @SerializedName("monitorPositions")
            val monitorPositions: String?,
            @SerializedName("needExtraParams")
            val needExtraParams: List<Any>?,
            @SerializedName("organization")
            val organization: String?,
            @SerializedName("playUrl")
            val playUrl: String?,
            @SerializedName("relative_index")
            val relativeIndex: Int?,
            @SerializedName("viewUrl")
            val viewUrl: String?
        )
    }
}