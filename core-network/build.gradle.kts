plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.conventer.gson)
    implementation(libs.koin.core)
}