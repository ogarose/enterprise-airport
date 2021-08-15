plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.leasing.domain"

dependencies {
    implementation(project(":common:types:domain"))
}