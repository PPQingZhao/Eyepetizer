@file:Suppress("UnstableApiUsage","DSL_SCOPE_VIOLATION")

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.android.application") version (libs.versions.androidGradlePlugin) apply false
    id("com.android.library") version (libs.versions.androidGradlePlugin) apply (false)
}
