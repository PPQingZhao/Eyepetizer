package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField

open class AutoPlayItemViewModel: VideoCardItemViewModel() {

    val description = ObservableField<String>()
    val name = ObservableField<String>()
    val publishDate = ObservableField<String>()
    val tag1 = ObservableField("")
    val tag2 = ObservableField("")
    val tag3 = ObservableField("")
    val collectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()

}