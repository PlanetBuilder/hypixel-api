import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//https://docs.gradle.org/current/userguide/publishing_maven.html

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.31"
    `maven-publish`
}

group = "de.planetbuilder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    implementation( platform("org.http4k:http4k-bom:4.16.1.0"))
    implementation("org.http4k:http4k-core:4.16.2.0")
    implementation("org.http4k:http4k-server-netty:4.16.2.0")
    implementation("org.http4k:http4k-client-apache:4.16.2.0")
    implementation("org.http4k:http4k-format-kotlinx-serialization:4.16.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.slf4j:slf4j-jdk14:1.7.32")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.planetbuilder"
            artifactId = "unofficial-hypixel-api"
            version = "0.0.1-SNAPSHOT"

            from(components["java"])
        }
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}