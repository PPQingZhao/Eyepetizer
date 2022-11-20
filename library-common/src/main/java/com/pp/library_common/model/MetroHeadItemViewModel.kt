package com.pp.library_common.model

import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_ui.model.HeaderItemViewModel

class MetroHeadItemViewModel(metroHead: Card.CardData.Header?): HeaderItemViewModel() {

    var head: Card.CardData.Header? = null
        set(value) {
            field = value
            val text = head?.left?.getOrNull(0)?.metroData?.text ?: ""
            leftText.set(text)
            val showArrow = head?.right?.getOrNull(0)?.style?.tplLabel == EyepetizerService2.MetroType.Style.more_link
            showMoreIcon.set(showArrow)
        }

    init {
        head = metroHead
    }

    override fun onMore() {

    }
}