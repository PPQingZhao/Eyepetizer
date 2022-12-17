package com.pp.library_common.model

import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_ui.model.FeedCoverViewModel

class MetroItemFeedCoverViewModel(m: Metro?): FeedCoverViewModel() {

    var metro: Metro? = null
        set(value) {
            field = value
            value?.metroData?.let {
                cover.set(it.cover.url)
                title.set(it.title)
                description.set(it.description ?: "")
                tagCount.set(it.tags?.getOrNull(0)?.title ?:"")
            }
        }

    init {
        metro = m
    }
}