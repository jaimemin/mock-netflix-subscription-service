dependencies {
    implementation(project(":netflix-core:core-usecase"))
    implementation(project(":netflix-core:core-domain"))
    implementation(project(":netflix-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-batch")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework:spring-tx")

    runtimeOnly(project(":netflix-core:core-service"))
}
