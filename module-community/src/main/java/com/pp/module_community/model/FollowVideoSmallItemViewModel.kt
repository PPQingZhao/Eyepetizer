package com.pp.module_community.model

import android.view.View
import androidx.databinding.ObservableField

open class FollowVideoSmallItemViewModel(val data: Any?) {
    val description = ObservableField<String>()
    val nickname = ObservableField<String>()
    val imagePath = ObservableField<String>()
    val avatarPath = ObservableField<String>()

    open fun onVideo(view: View) {}

    open fun onUser(view: View) {}

    open fun onLike(view: View) {}
}