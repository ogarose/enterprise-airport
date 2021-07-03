plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.flightmanagement.domain"

dependencies {
    implementation(project(":common:types"))
}