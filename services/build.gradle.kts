import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.withType<Jar> {
    enabled = false
}

tasks.withType<BootJar> {
    enabled = false
}