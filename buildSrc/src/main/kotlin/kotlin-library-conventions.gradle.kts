plugins {
  id("kotlin-common-conventions")
  java
  `java-library`
  `maven-publish`
  signing
}

java {
  withSourcesJar()
  withJavadocJar()
}

publishing {
  repositories {
    maven {
      name = "GithubPackages"
      url = uri("https://maven.pkg.github.com/CHANGEME/CHANGEME")
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
  publications {
    create<MavenPublication>(project.name) {
      from(components["kotlin"])
      artifact(tasks.findByName("sourcesJar"))
      artifact(tasks.findByName("javadocJar"))
      groupId = project.group.toString()
      artifactId = project.name.toLowerCase()
      version = project.version.toString()

      pom {
        name.set("CHANGEME")
        description.set("CHANGEME")
        url.set("https://github.com/CHANGEME/CHANGEME")
        licenses {
          license {
            name.set("MIT License")
            url.set("https://mit-license.org/")
          }
        }
        developers {
          developer {
            id.set("CHANGEME")
            name.set("Ryan Brink")
            email.set("admin@bkbn.io")
          }
        }
        scm {
          connection.set("scm:git:git://github.com/CHANGEME/CHANGEME.git")
          developerConnection.set("scm:git:ssh://github.com/CHANGEME/CHANGEME.git")
          url.set("https://github.com/CHANGEME/CHANGEME.git")
        }
      }
    }
  }
}

if ((project.findProperty("release") as? String)?.toBoolean() == true) {
  signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
  }
}
