plugins {
    `java-library`
    `maven-publish`
}

dependencies {
    implementation(libs.bundles.logging)
    testImplementation(libs.bundles.test)
    detektPlugins(libs.detekt.formatting)
}