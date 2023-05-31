import java.text.SimpleDateFormat
import java.util.*

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
}

android {
    defaultConfig {
        applicationId = "com.fphoenixcorneae.cloud.music"
        namespace = applicationId
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 100
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        ndk {
            // 设置支持的SO库架构
            abiFilters.addAll(listOf("armeabi-v7a", "x86"))  //'armeabi', 'x86', 'armeabi-v7a', 'x86_64', 'arm64-v8a'
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.kotlinCompilerExtension.get()
    }

    lint {
        checkDependencies = true
        checkReleaseBuilds = false
        abortOnError = false
    }
}

// 输出文件
android.applicationVariants.all {
    outputs.all {
        // 输出 Apk
        if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
            outputFileName = "${project.name}_V${android.defaultConfig.versionName}_${buildType.name}_${
                SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Date())
            }.apk"
        }
    }
}

dependencies {
    // compose
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.datastore)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.coil.compose)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)
    debugImplementation(libs.androidx.compose.ui.tooling)
}