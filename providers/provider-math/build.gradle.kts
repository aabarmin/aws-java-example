plugins {
    id("dev.abarmin.spring-cloud-conventions")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    implementation(project(":providers:provider-base"))
}