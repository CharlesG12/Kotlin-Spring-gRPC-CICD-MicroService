import java.io.*
import java.util.*

// read gradle.properties programmatically
val props = Properties()
FileInputStream(file("../gradle.properties")).use {
    props.load(it)
}

val kotlinVersion = props.getProperty("kotlinVersion")
val springBootVersion = props.getProperty("springBootVersion")

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
}