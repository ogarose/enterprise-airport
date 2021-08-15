plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.maintenance.usecase"

dependencies {
    implementation(project(":common:types:domain"))
    implementation(project(":common:types:usecase"))
    implementation(project(":maintenance:domain"))
}