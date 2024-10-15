dependencies {
    implementation(project(":netflix-core:core-usecase"))
    implementation(project(":netflix-commons"))

    runtimeOnly(project(":netflix-adapters:adapter-http"))
    runtimeOnly(project(":netflix-core:core-service"))

    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.security:spring-security-oauth2-client")
}

val appMainClassName = "com.tistory.jaimemin.mocknetflix.NetflixApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}