plugins {
    id("airport.java-common-conventions")

    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("net.researchgate.release")
}

//val appVersion: String by project.extra("version")

group = "com.enterprise.airport"
version = rootProject.version
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.bootBuildImage {
    imageName = "airport/mainapp"
}