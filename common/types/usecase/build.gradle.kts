plugins {
    id("airport.java-common-conventions")
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}

dependencies {
    implementation(project(":common:types:domain"))
}