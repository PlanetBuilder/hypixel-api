import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    application
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "de.planetbuilder"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("de.planetbuilder:unofficial-hypixel-api:0.0.1-SNAPSHOT")

    implementation(platform("org.http4k:http4k-bom:4.8.0.0"))
    implementation("org.http4k:http4k-core:4.16.3.0")
    implementation("org.http4k:http4k-server-jetty:4.16.3.0")
    implementation("org.http4k:http4k-server-apache4:4.16.3.0")
    implementation("org.http4k:http4k-client-websocket:4.16.3.0")
    implementation("org.http4k:http4k-format-jackson:4.16.3.0")
    implementation("org.http4k:http4k-format-kotlinx-serialization:4.16.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

    implementation("org.slf4j:slf4j-jdk14:1.7.32")

    implementation("com.google.guava:guava:31.0.1-jre")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}