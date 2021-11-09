import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}