import org.gradle.api.artifacts.dsl.DependencyHandler

internal val kotlinDependencies = listOf(
    Deps.Kotlin.stdlib,
    Deps.Kotlin.coroutinesCore,
    Deps.Kotlin.coroutinesAndroid,
)

internal val androidXDependencies = listOf(
    Deps.AndroidX.multiDex,
    Deps.AndroidX.appcompat,
    Deps.AndroidX.coreKtx,
    Deps.AndroidX.material,
    Deps.AndroidX.paletteKtx,
    Deps.AndroidX.pagingRuntime,
    Deps.AndroidX.startupRuntime,
)

internal val lifecycleDependencies = listOf(
    Deps.Lifecycle.runtimeKtx,
    Deps.Lifecycle.commonJava8,
    Deps.Lifecycle.liveDataKtx,
    Deps.Lifecycle.viewModelKtx,
    Deps.Lifecycle.process,
)

internal val composeOfficialDependencies = listOf(
    Deps.Compose.ui,
    Deps.Compose.uiTooling,
    Deps.Compose.material,
    Deps.Compose.materialIconsCore,
    Deps.Compose.materialIconsExtended,
    Deps.Compose.runtimeLivedata,
    Deps.Compose.constraintLayout,
    Deps.Compose.paging,
    Deps.Compose.lifecycleViewModel,
    Deps.Compose.activity,
    Deps.Compose.navigation,
)

internal val composeThirdPartyDependencies = listOf(
    Deps.ComposeThirdParty.coil,
    Deps.ComposeThirdParty.lottie,
)

internal val thirdPartyDependencies = listOf(
    Deps.ThirdParty.mmkv,
)

internal val fPhoenixCornerEDependencies = listOf(
    Deps.FPhoenixCorneaE.commonUtil,
    Deps.FPhoenixCorneaE.commonToolbar,
    Deps.FPhoenixCorneaE.jetpackMvvmCompose,
    Deps.FPhoenixCorneaE.coRetrofit,
)

internal val debugDependencies = listOf(
    Deps.Debug.kotlinReflect,
    Deps.Debug.uiTooling,
    Deps.Debug.uiTestManifest,
)

internal val testDependencies = listOf(
    Deps.Test.junit,
    Deps.Test.kotlinTest,
)

internal val androidTestDependencies = listOf(
    Deps.AndroidTest.junit,
    Deps.AndroidTest.espressoCore,
    Deps.AndroidTest.uiTestJunit4,
    Deps.AndroidTest.uiTestManifest,
)

/**
 * 添加 kotlin 依赖库
 */
fun DependencyHandler.addKotlinDependencies() {
    kotlinDependencies.forEach {
        add("implementation", it)
    }
}

/**
 * 添加 androidX 依赖库
 */
fun DependencyHandler.addAndroidXDependencies() {
    androidXDependencies.forEach {
        add("implementation", it)
    }
}

/**
 * 添加 lifecycle 依赖库
 */
fun DependencyHandler.addLifecycleDependencies() {
    lifecycleDependencies.forEach {
        add("api", it)
    }
}

/**
 * 添加 compose 官方依赖库
 */
fun DependencyHandler.addComposeOfficialDependencies() {
    composeOfficialDependencies.forEach {
        add("api", it)
    }
}

/**
 * 添加 compose 第三方依赖库
 */
fun DependencyHandler.addComposeThirdPartyDependencies() {
    composeThirdPartyDependencies.forEach {
        add("api", it)
    }
}

/**
 * 添加 kotlin 依赖库
 */
fun DependencyHandler.addThirdPartyDependencies() {
    thirdPartyDependencies.forEach {
        add("api", it)
    }
}

/**
 * 添加 FPhoenixCorneaE 依赖库
 */
fun DependencyHandler.addFPhoenixCorneaEDependencies() {
    fPhoenixCornerEDependencies.forEach {
        add("api", it)
    }
}

/**
 * 添加调试依赖库
 */
fun DependencyHandler.addDebugDependencies() {
    debugDependencies.forEach {
        add("debugImplementation", it)
    }
}

/**
 * 添加测试依赖库
 */
fun DependencyHandler.addTestDependencies() {
    testDependencies.forEach {
        add("testImplementation", it)
    }
}

/**
 * 添加 android 测试依赖库
 */
fun DependencyHandler.addAndroidTestDependencies() {
    androidTestDependencies.forEach {
        add("androidTestImplementation", it)
    }
}