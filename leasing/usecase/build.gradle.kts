plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.leasing.usecase"

dependencies {
    implementation(project(":common:types:domain"))
    implementation(project(":common:types:usecase"))
    implementation(project(":leasing:domain"))
}