// Tracking -> https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(build.plugins.git.hooks)
  alias(build.plugins.nexus.publish)
}

gitHooks {
  setHooks(
    mapOf(
      "pre-commit" to "detekt",
      "pre-push" to "test"
    )
  )
}

nexusPublishing {
  repositories {
    sonatype {
      nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
      snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
    }
  }
}
