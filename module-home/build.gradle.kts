plugins {
    alias(libs.plugins.android.module)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {

    namespace = "com.pp.module_home"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["AROUTER_MODULE_NAME"] = project.name
            }
        }
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

    if ("com.android.application" == (libs.plugins.android.module.get().pluginId)) {
        sourceSets["main"].java.srcDir("src/main/module")
        sourceSets["main"].manifest.srcFile("src/main/module/AndroidManifest.xml")
        sourceSets["main"].res.srcDir("src/main/res-module")
    } else {
        sourceSets["main"].resources.exclude("src/main/module/*")
        sourceSets["main"].resources.exclude("src/main/res-module/*")
    }
}

dependencies {

    // 路由
    implementation(libs.arouter.api)
    kapt(libs.arouter.compiler)

    implementation(projects.libraryCommon)
}