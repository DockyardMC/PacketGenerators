plugins {
    `maven-publish`
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.23"
    application
}

group = "io.github.dockyardmc"
version = "1.1"

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
    implementation("org.jsoup:jsoup:1.17.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}

publishing {
    repositories {
        maven {
            url = uri("https://mvn.devos.one/releases")
            credentials {
                username = System.getenv()["MAVEN_USER"]
                password = System.getenv()["MAVEN_PASS"]
            }
        }
    }
    publications {
        register<MavenPublication>("maven") {
            groupId = "io.github.dockyardmc"
            artifactId = "wikivg-datagen"
            version = version
            from(components["java"])
        }
    }
}