plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.23"
    application
}

group = "io.github.dockyardmc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    repositories {
        maven {
            name = "devOS"
            url = uri("https://mvn.devos.one/releases")
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("cz.lukynka:pretty-log:1.4")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}