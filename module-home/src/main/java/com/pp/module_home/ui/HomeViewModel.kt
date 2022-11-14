package com.pp.module_home.ui

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.pp.mvvm.LifecycleViewModel

class HomeViewModel(app: Application) : LifecycleViewModel(app) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }
}
