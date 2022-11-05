package com.pp.library_common.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val CONFIG_NAME = "config_setting"
val Context.dataStore by preferencesDataStore(name = CONFIG_NAME)