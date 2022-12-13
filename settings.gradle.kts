@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.aliyun.com/repository/public")
//        maven("https://maven.google.com")
    }
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

// Generate type safe accessors when referring to other projects eg.
// Before: implementation(project(":feature-album"))
// After: implementation(projects.featureAlbum)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Eyepetizer"

include(
    ":app",
    ":library-mvvm",
    ":library-base",
    ":library-ui",
    ":library-router-service",
    ":library-network",
    ":library-common",
    ":library-database",
    ":library-theme",
    ":module-main",
    ":module-home",
    ":module-community",
    ":module-video-details",
    ":module-comments",
    ":module-search",
    ":module-user",
    ":module-discovery"
)
