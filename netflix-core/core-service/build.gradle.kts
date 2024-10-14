dependencies {
    implementation(project(":netflix-core:core-port"))
    implementation(project(":netflix-core:core-usecase"))

    runtimeOnly(project(":netflix-adapters:adapter-http"))
    runtimeOnly(project(":netflix-adapters:adapter-persistence"))
    runtimeOnly(project(":netflix-adapters:adapter-redis"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")
}
