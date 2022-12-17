package com.pp.library_ui.model

import androidx.databinding.ObservableField

open class FeedCoverViewModel {
    val cover = ObservableField<String>()
    val title = ObservableField<String>()
    val description = ObservableField<String>()
    val tagCount = ObservableField<String>()

    open fun onItem() {}
}