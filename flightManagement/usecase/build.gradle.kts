plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.flightmanagement.usecase"

dependencies {
    implementation(project(":common:types"))
    implementation(project(":flightManagement:domain"))
}