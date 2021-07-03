plugins {
    id("airport.java-common-conventions")
}

group = "com.enterprise.airport.common.types"

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}