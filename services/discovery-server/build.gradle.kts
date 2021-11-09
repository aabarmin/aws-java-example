import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

extra["springCloudVersion"] = "2020.0.4"

tasks.withType<BootBuildImage> {
    imageName = "application/service-discovery"
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
}