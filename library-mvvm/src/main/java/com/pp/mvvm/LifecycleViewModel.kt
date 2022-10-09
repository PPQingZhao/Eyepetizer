package com.pp.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver

open class LifecycleViewModel(app: Application) : AndroidViewModel(app), DefaultLifecycleObserver {
}