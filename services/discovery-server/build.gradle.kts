import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("dev.abarmin.spring-cloud-conventions")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

tasks.withType<BootBuildImage> {
    imageName = "application/service-discovery:oci"
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
}