plugins {
  `java-library`
  `maven-publish`
  signing
}

dependencies {
    implementation(libs.bundles.logging)
    testImplementation(libs.bundles.test)
    detektPlugins(libs.detekt.formatting)
}

publishing {
  publications {
    create<MavenPublication>("changeme") {
      from(components["kotlin"])
      artifact(tasks.sourcesJar)
      artifact(tasks.javadocJar)

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

signing {
  val signingKey: String? by project
  val signingPassword: String? by project
  useInMemoryPgpKeys(signingKey, signingPassword)
  sign(publishing.publications)
}
