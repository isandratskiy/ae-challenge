import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("java")
    id("com.adarshr.test-logger") version "3.2.0"
}

group = "com.tunein.challenge"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.junit)
    implementation(libs.selenide)
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    val smoke = "smoke"
    val regression = "regression"

    register<Test>(smoke) {
        useJUnitPlatform {
            includeTags = setOf(smoke)
        }
    }

    register<Test>(regression) {
        useJUnitPlatform {
            includeTags = setOf(regression)
        }
    }
}

testlogger {
    theme = ThemeType.MOCHA
    showExceptions = true
    showStackTraces = true
    showFullStackTraces = false
    showCauses = true
    slowThreshold = 1000
    showSummary = true
    showSimpleNames = true
    showPassed = true
    showSkipped = false
    showFailed = true
    showOnlySlow = false
    showStandardStreams = false
    showPassedStandardStreams = false
    showSkippedStandardStreams = false
    showFailedStandardStreams = true
    logLevel = LogLevel.LIFECYCLE
}
