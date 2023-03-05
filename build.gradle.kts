import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
    id("de.jensklingenberg.ktorfit") version "1.0.0"
    id("org.beryx.runtime") version "1.13.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
    application
    jacoco
}

group = "me.scalderone"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

val ktorVersion = "2.2.4"
val ktorfitVersion = "1.0.0"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("ch.qos.logback:logback-core:1.4.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.1")

    implementation("de.jensklingenberg.ktorfit:ktorfit-lib:$ktorfitVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    ksp("de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")

}

// TODO: configure separate tasks to run integration or unit tests
tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "19"
}

application {
    mainClass.set("MainKt")
}