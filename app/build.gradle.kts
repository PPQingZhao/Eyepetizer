plugins {
    alias(libs.plugins.kotlin.android)
    id("com.android.application") version (libs.versions.androidGradlePlugin)
    id("kotlin-kapt")
}

android {
    namespace = "com.pp.eyepetizer"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.pp.eyepetizer"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    implementation(projects.libraryCommon)
    implementation(projects.moduleMain)
    if ("com.android.library" == (libs.plugins.android.module.get().pluginId)) {
        implementation(projects.moduleHome)
        implementation(projects.moduleCommunity)
        implementation(projects.moduleVideoDetails)
        implementation(projects.moduleComments)
    }
//    print(libs.plugins.android.module.get().pluginId)

}