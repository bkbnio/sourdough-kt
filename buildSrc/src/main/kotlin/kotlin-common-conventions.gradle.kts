import com.adarshr.gradle.testlogger.theme.ThemeType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  idea
  java
  jacoco
}

group = "io.bkbn"
version = run {
  val baseVersion =
    project.findProperty("project.version") ?: error("project.version needs to be set in gradle.properties")
  when ((project.findProperty("release") as? String)?.toBoolean()) {
    true -> baseVersion
    else -> "$baseVersion-SNAPSHOT"
  }
}

repositories {
  mavenCentral()
}

jacoco {
  toolVersion = "0.8.7"
}

tasks.withType<Test>() {
  finalizedBy(tasks.withType(JacocoReport::class))
}

tasks.withType<JacocoReport>() {
  reports {
    html.required.set(true)
    xml.required.set(true)
  }
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

java {
  withSourcesJar()
  withJavadocJar()
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
  }
}

detekt {
  toolVersion = "1.19.0-RC2"
  config = files("${rootProject.projectDir}/detekt.yml")
  buildUponDefaultConfig = true
}

testlogger {
  theme = ThemeType.MOCHA
  setLogLevel("lifecycle")
  showExceptions = true
  showStackTraces = true
  showFullStackTraces = false
  showCauses = true
  slowThreshold = 2000
  showSummary = true
  showSimpleNames = false
  showPassed = true
  showSkipped = true
  showFailed = true
  showStandardStreams = false
  showPassedStandardStreams = true
  showSkippedStandardStreams = true
  showFailedStandardStreams = true
}

dependencies {
  // Standard Lib
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  // Logging
  implementation("org.slf4j:slf4j-api:1.7.30")
  implementation("ch.qos.logback:logback-classic:1.2.3")
  implementation("ch.qos.logback:logback-core:1.2.3")

  // Formatting
  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0-RC2")
}

testing {
  suites {
    named<JvmTestSuite>("test") {
      useJUnitJupiter()

      dependencies {
        implementation("io.kotest:kotest-runner-junit5:4.4.3")
        implementation("io.kotest:kotest-assertions-core-jvm:4.4.3")
        implementation("io.kotest:kotest-assertions-json-jvm:4.4.3")
        implementation("io.kotest:kotest-property-jvm:4.4.3")
        implementation("io.mockk:mockk:1.12.0")
      }
    }
    create<JvmTestSuite>("testIntegration") {
      useJUnitJupiter()

      dependencies {
        implementation("io.kotest:kotest-runner-junit5:4.4.3")
        implementation("io.kotest:kotest-assertions-core-jvm:4.4.3")
        implementation("io.kotest:kotest-assertions-json-jvm:4.4.3")
        implementation("io.kotest:kotest-property-jvm:4.4.3")
      }
    }
  }
}
