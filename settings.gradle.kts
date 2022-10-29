pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
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
include(":app")
include(":library-mvvm")
include(":library-base")
include(":library-ui")
include(":library-router-service")
include(":library-network")
include(":library-common")
include(":module-main")
include(":module-home")
include(":module-community")
include(":module-video-details")
include(":module_comments")
