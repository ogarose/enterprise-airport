plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.maintenance.usecase"

dependencies {
    implementation(project(":common:types"))
    implementation(project(":maintenance:domain"))
}