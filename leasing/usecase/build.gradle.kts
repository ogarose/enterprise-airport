plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.leasing.usecase"

dependencies {
    implementation(project(":common:types"))
    implementation(project(":leasing:domain"))
}