plugins {
    id("airport.java-common-conventions")
}

dependencies {
    implementation(project(":common:types:domain"))
    implementation(project(":common:types:usecase"))
    implementation(project(":flightManagement:domain"))
}