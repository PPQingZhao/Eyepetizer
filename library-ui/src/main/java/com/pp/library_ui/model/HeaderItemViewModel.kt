package com.pp.library_ui.model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

abstract class HeaderItemViewModel {

    val leftText = ObservableField<String>()
    val showMoreIcon = ObservableBoolean()

    abstract fun onMore()
}