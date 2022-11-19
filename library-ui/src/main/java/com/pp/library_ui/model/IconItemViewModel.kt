package com.pp.library_ui.model

import androidx.databinding.ObservableField

open class IconItemViewModel {
    val iconUrl = ObservableField<String>()
    val name = ObservableField<String>()

    open fun onIcon() {

    }
}