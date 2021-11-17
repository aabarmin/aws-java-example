import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("dev.abarmin.spring-cloud-conventions")
}

tasks.withType<BootBuildImage> {
    imageName = "application/provider-math:oci"
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    implementation(project(":providers:provider-base"))
}