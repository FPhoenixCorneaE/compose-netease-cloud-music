import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        compileSdk = DefaultConfig.compileSdkVersion
        minSdk = DefaultConfig.minSdkVersion
        targetSdk = DefaultConfig.targetSdkVersion
    }

    sourceSets {
        val main by getting
        main.java.srcDirs("src/main/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    lint {
        isCheckDependencies = true
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
}
