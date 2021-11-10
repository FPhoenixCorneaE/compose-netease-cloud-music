import java.text.SimpleDateFormat
import java.util.*

/**
 * @desc：所有依赖库按照规范写在这里，用到的地方，通过 Deps 引用即可。在添加依赖之前，请检查该文件中是否已存在相
 *        同的依赖，如若已经添加相同依赖，则无需再重复添加，请注意。
 * @date：2021/1/16 19:08
 */
object Deps {

    object FPhoenixCorneaE {
        const val commonUtil = "com.github.FPhoenixCorneaE:CommonUtil:1.1.2"
        const val commonToolbar = "com.github.FPhoenixCorneaE:CommonToolbar:2.0.0"
        const val jetpackMvvmCompose = "com.github.FPhoenixCorneaE:JetpackMvvmCompose:1.0.5"
        const val coRetrofit = "com.github.FPhoenixCorneaE:CoRetrofit:1.0.1"
    }

    object BuildType {
        const val Debug = "debug"
        const val Release = "release"
    }

    /**
     * Kotlin
     */
    object Kotlin {
        private const val coroutinesVersion = "1.5.1"
        const val kotlinVersion = "1.5.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    /**
     * AndroidX
     */
    object AndroidX {
        const val multiDex = "androidx.multidex:multidex:2.0.1"
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val coreSplashScreen = "androidx.core:core-splashscreen:1.0.0-alpha02"
        const val material = "com.google.android.material:material:1.4.0"
        const val paletteKtx = "androidx.palette:palette-ktx:1.0.0"
        const val pagingRuntime = "androidx.paging:paging-runtime-ktx:3.0.1"
        const val startupRuntime = "androidx.startup:startup-runtime:1.0.0"
    }

    /**
     * Jetpack Lifecycle
     */
    object Lifecycle {
        private const val version = "2.3.1"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val process = "androidx.lifecycle:lifecycle-process:$version"
    }

    /**
     * Jetpack Compose
     */
    object Compose {
        const val version = "1.0.1"
        const val ui = "androidx.compose.ui:ui:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val material = "androidx.compose.material:material:$version"
        const val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
        const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
        const val paging = "androidx.paging:paging-compose:1.0.0-alpha12"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
        const val activity = "androidx.activity:activity-compose:1.3.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha05"
    }

    /**
     * Compose Third Party
     */
    object ComposeThirdParty {
        const val lottie = "com.airbnb.android:lottie-compose:4.0.0"
        const val coil = "io.coil-kt:coil-compose:1.3.2"
    }

    /**
     * ThirdParty
     */
    object ThirdParty {
        const val mmkv = "com.tencent:mmkv-static:1.2.9"
    }

    /**
     * Debug
     */
    object Debug {
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Kotlin.kotlinVersion}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Compose.version}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.version}"
    }

    /**
     * Test
     */
    object Test {
        const val junit = "junit:junit:4.13.2"
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Kotlin.kotlinVersion}"
    }

    /**
     * AndroidTest
     */
    object AndroidTest {
        const val junit = "androidx.test.ext:junit:1.1.2"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.version}"
    }

    /**
     * 当前时间
     */
    fun getSystemTime(): String {
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
        return simpleDateFormat.format(System.currentTimeMillis())
    }
}
