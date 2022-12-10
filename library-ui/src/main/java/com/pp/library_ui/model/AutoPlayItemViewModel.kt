package com.pp.library_ui.model

import android.view.View
import androidx.databinding.ObservableField

open class AutoPlayItemViewModel: VideoCardItemViewModel() {

    val description = ObservableField<String>()
    val name = ObservableField<String>()
    val publishDate = ObservableField<String>()
    val tag1 = ObservableField<String>()
    val tag2 = ObservableField<String>()
    val tag3 = ObservableField<String>()
    val collectionCount = ObservableField<String>()
    val replyCount = ObservableField<String>()
    val realCollectionCount = ObservableField<String>()

}