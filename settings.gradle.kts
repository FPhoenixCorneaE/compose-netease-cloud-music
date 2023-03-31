pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    versionCatalogs {
        create("libs") {
            // version
            version("compileSdk", "33")
            version("minSdk", "21")
            version("targetSdk", "33")
            version("android", "7.4.1")
            version("kotlin", "1.7.0")
            version("compose", "1.2.0")
            version("accompanist", "0.25.1")

            // plugin
            plugin("android-application", "com.android.application").versionRef("android")
            plugin("android-library", "com.android.library").versionRef("android")
            plugin("kotlin-android", "org.jetbrains.kotlin.android").versionRef("kotlin")
            plugin("kotlin-kapt", "org.jetbrains.kotlin.kapt").versionRef("kotlin")
            plugin("kotlin-parcelize", "org.jetbrains.kotlin.plugin.parcelize").versionRef("kotlin")

            // library
            library("androidx-compose-animation", "androidx.compose.animation", "animation").versionRef("compose")
            library("androidx-compose-foundation", "androidx.compose.foundation", "foundation").versionRef("compose")
            library("androidx-compose-material", "androidx.compose.material", "material").versionRef("compose")
            library("androidx-compose-material-icons-core", "androidx.compose.material", "material-icons-core")
                .versionRef("compose")
            library("androidx-compose-material-icons-extended", "androidx.compose.material", "material-icons-extended")
                .versionRef("compose")
            library("androidx-compose-ui", "androidx.compose.ui", "ui").versionRef("compose")
            library("androidx-compose-ui-tooling", "androidx.compose.ui", "ui-tooling").versionRef("compose")
            library("androidx-compose-ui-tooling-preview", "androidx.compose.ui", "ui-tooling-preview")
                .versionRef("compose")
            bundle(
                "compose", listOf(
                    "androidx-compose-animation",
                    "androidx-compose-foundation",
                    "androidx-compose-material",
                    "androidx-compose-ui",
                    "androidx-compose-ui-tooling-preview",
                )
            )
            library("androidx-core-ktx", "androidx.core:core-ktx:1.9.0")
            library("androidx-constraintlayout-compose", "androidx.constraintlayout:constraintlayout-compose:1.0.1")
            library("androidx-activity-compose", "androidx.activity:activity-compose:1.6.1")
            library("androidx-datastore", "androidx.datastore:datastore-preferences:1.0.0")
            library("lifecycle-viewmodel-compose", "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
            library("lifecycle-viewmodel-savedstate", "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1")
            library("accompanist-insets", "com.google.accompanist", "accompanist-insets")
                .versionRef("accompanist")
            library("accompanist-insets-ui", "com.google.accompanist", "accompanist-insets-ui")
                .versionRef("accompanist")
            library("accompanist-systemuicontroller", "com.google.accompanist", "accompanist-systemuicontroller")
                .versionRef("accompanist")
            library("accompanist-permissions", "com.google.accompanist", "accompanist-permissions")
                .versionRef("accompanist")
            library("accompanist-placeholder-material", "com.google.accompanist", "accompanist-placeholder-material")
                .versionRef("accompanist")
            library("accompanist-navigation-animation", "com.google.accompanist", "accompanist-navigation-animation")
                .versionRef("accompanist")
            library("accompanist-navigation-material", "com.google.accompanist", "accompanist-navigation-material")
                .versionRef("accompanist")
            bundle(
                "accompanist", listOf(
                    "accompanist-insets",
                    "accompanist-insets-ui",
                    "accompanist-systemuicontroller",
                    "accompanist-permissions",
                    "accompanist-placeholder-material",
                    "accompanist-navigation-animation",
                    "accompanist-navigation-material",
                )
            )
            library("coil-compose", "io.coil-kt:coil-compose:1.3.2")
            library("retrofit2-retrofit", "com.squareup.retrofit2:retrofit:2.9.0")
            library("retrofit2-converter-gson", "com.squareup.retrofit2:converter-gson:2.9.0")
            library("okhttp3-logging-interceptor", "com.squareup.okhttp3:logging-interceptor:4.9.1")
        }
    }
}
// 开启 VERSION_CATALOGS
enableFeaturePreview("VERSION_CATALOGS")
// 依赖project时提供类型安全的访问器
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
rootProject.name = "netease-cloud-music-compose"