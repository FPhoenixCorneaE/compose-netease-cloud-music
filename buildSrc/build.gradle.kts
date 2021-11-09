plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    google()
    mavenCentral()
}

dependencies {
    // in order to be able to use "kotlin-android" in the common script
    implementation(GradlePlugin.kotlin)
    // in order to recognize the "plugins" block in the common script
    implementation(GradlePlugin.android)
    // in order to recognize the "android" block in the common script
    implementation(GradlePlugin.androidApi)
}

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}