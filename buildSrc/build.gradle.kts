plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.9.10"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(17)
}