plugins {
    id("airport.java-common-conventions")
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.enterprise.airport.integration"

dependencies {
    // web dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")

    // project dependencies
    implementation(project(":flightManagement:domain"))
    implementation(project(":common:types:domain"))

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}