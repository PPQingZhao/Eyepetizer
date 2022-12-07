package com.pp.library_ui.model

import androidx.databinding.ObservableField

sealed class ImageVideoItemViewModel(
    val cover: ObservableField<String>,
    val videoType: Boolean = false
) {
    class VideoItemViewModel(cover: ObservableField<String>, val playUrl: ObservableField<String>) :
        ImageVideoItemViewModel(cover, true)

    class ImageItemViewModel(cover: ObservableField<String>) : ImageVideoItemViewModel(cover, false)
}