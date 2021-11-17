import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("dev.abarmin.spring-cloud-conventions")
}

tasks.withType<BootBuildImage> {
    imageName = "application/challenges-provider:oci"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}