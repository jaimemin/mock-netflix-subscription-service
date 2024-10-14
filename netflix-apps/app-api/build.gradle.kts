dependencies {
    implementation(project(":netflix-core:core-usecase"))

    runtimeOnly(project(":netflix-adapters:adapter-http"))
    runtimeOnly(project(":netflix-core:core-service"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}
