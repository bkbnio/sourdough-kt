import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType

// Tracking -> https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(build.plugins.kotlin) apply false
  alias(build.plugins.serialization) apply false
  alias(build.plugins.detekt) apply false
  alias(build.plugins.test.logger) apply false
  alias(build.plugins.git.hooks) apply true
  alias(build.plugins.nexus.publish) apply true
}

gitHooks {
  setHooks(
    mapOf(
      "pre-commit" to "detekt",
      "pre-push" to "test"
    )
  )
}

subprojects {
  group = "changeme"
  version = run {
    val baseVersion =
      project.findProperty("project.version") ?: error("project.version must be set in gradle.properties")
    when ((project.findProperty("release") as? String)?.toBoolean()) {
      true -> baseVersion
      else -> "$baseVersion-SNAPSHOT"
    }
  }

  repositories {
    mavenCentral()
    mavenLocal()
  }

  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
  apply(plugin = "io.gitlab.arturbosch.detekt")
  apply(plugin = "com.adarshr.test-logger")
  apply(plugin = "jacoco")
  apply(plugin = "java-library")
  apply(plugin = "java-test-fixtures")
  apply(plugin = "maven-publish")
  apply(plugin = "idea")

  tasks.withType<Test>() {
    finalizedBy(tasks.withType(JacocoReport::class))
  }

  configure<TestLoggerExtension> {
    theme = ThemeType.MOCHA
    logLevel = LogLevel.LIFECYCLE
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

  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = "11"
    }
  }

  configure<DetektExtension> {
    toolVersion = project.findProperty("detektVersion").toString()
    config = files("${rootProject.projectDir}/detekt.yml")
    buildUponDefaultConfig = true
  }

  configure<JavaPluginExtension> {
    withSourcesJar()
    withJavadocJar()
  }

  configure<PublishingExtension> {
    repositories {
      maven {
        name = "GithubPackages"
        url = uri("https://maven.pkg.github.com/changeme/changeme")
        credentials {
          username = System.getenv("GITHUB_ACTOR")
          password = System.getenv("GITHUB_TOKEN")
        }
      }
    }
    publications {
      create<MavenPublication>("changeme") {
        groupId = project.group.toString()
        artifactId = project.name.toLowerCase()
        version = project.version.toString()

        pom {
          name.set("changeme")
          description.set("changeme")
          url.set("https://github.com/changeme/changeme")
          licenses {
            license {
              name.set("MIT License")
              url.set("https://mit-license.org/")
            }
          }
          developers {
            developer {
              id.set("changeme")
              name.set("changeme")
              email.set("changeme")
            }
          }
          scm {
            connection.set("scm:git:git://github.com/changeme/changeme.git")
            developerConnection.set("scm:git:ssh://github.com/changeme/changeme.git")
            url.set("https://github.com/changeme/changeme.git")
          }
        }
      }
    }
  }

  configure<JacocoPluginExtension> {
    toolVersion = project.findProperty("jacocoVersion").toString()
  }

  configure<JavaPluginExtension> {
    toolchain {
      languageVersion.set(JavaLanguageVersion.of(11))
      vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    }
  }
}

nexusPublishing {
  repositories {
    sonatype {
      nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
      snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
    }
  }
}
