package com.pp.library_ui.model

import androidx.databinding.ObservableField

sealed class ImageVideoItemViewModel<T>(
    val cover: ObservableField<String>,
    val videoType: Boolean = false,
    val data: T,
) {
    class VideoItemViewModel<T>(
        cover: ObservableField<String>,
        val playUrl: ObservableField<String>,
        data: T,
    ) :
        ImageVideoItemViewModel<T>(cover, true, data)

    class ImageItemViewModel<T>(cover: ObservableField<String>, data: T) :
        ImageVideoItemViewModel<T>(cover, false, data)
}