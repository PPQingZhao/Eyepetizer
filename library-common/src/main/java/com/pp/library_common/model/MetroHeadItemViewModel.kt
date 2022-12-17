package com.pp.library_common.model

import android.util.Log
import com.google.gson.Gson
import com.pp.library_common.extension.intentToTopicList
import com.pp.library_common.extension.intentToTopicSquare
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.ApiRequest
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_ui.model.HeaderItemViewModel
import java.net.URLDecoder

class MetroHeadItemViewModel(metroHead: Card.CardData.Header?) : HeaderItemViewModel() {

    var head: Card.CardData.Header? = null
        set(value) {
            field = value
            val text = head?.left?.getOrNull(0)?.metroData?.text ?: ""
            leftText.set(text)
            val showArrow =
                head?.right?.getOrNull(0)?.style?.tplLabel == EyepetizerService2.MetroType.Style.more_link
            showMoreIcon.set(showArrow)
        }

    init {
        head = metroHead
    }

    override fun onMore() {
        head?.apply {
            right.getOrNull(0)?.metroData?.link?.let { link ->
                val url = URLDecoder.decode(link, "UTF-8")
                Log.e("TAG", "onMore url: $url")

                val types = url.split("?")
                val tag = types.getOrNull(0) ?: ""
                if (tag.contains("cardlist")) {
                    // card list
                    types.getOrNull(1)?.run {
                        val splits = split("api_request=")
                        splits.getOrNull(1)?.run {
                            val apiRequest = Gson().fromJson(this, ApiRequest::class.java)
                            // to detail list 主题播单
                            intentToTopicList(apiRequest)
                        }
                    }
                } else if (tag.contains("container")) {
                    //
                    types.getOrNull(1)?.run {
                        val type = replace("type=", "")
                        // to nav list 话题广场
                        intentToTopicSquare(type)
                    }
                }
            }
        }
    }
}