plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.maintenance.domain"

dependencies {
    implementation(project(":common:types:domain"))
}