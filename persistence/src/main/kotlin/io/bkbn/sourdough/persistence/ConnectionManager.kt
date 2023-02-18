package io.bkbn.sourdough.persistence

import org.flywaydb.core.Flyway
import org.komapper.jdbc.JdbcDatabase

object ConnectionManager {

  fun cleanMigrations() {
    flyway.clean()
  }

  fun performMigrations() {
    flyway.migrate()
  }

  private val flyway: Flyway by lazy {
    Flyway.configure().apply {
      dataSource(PostgresConfig.CONNECTION_URI, PostgresConfig.USER, PostgresConfig.PASSWORD)
      cleanDisabled(false)
    }.load() ?: error("Problem Loading Flyway!! Please verify Database Connection / Migration Info")
  }

  val database: JdbcDatabase by lazy {

    JdbcDatabase(PostgresConfig.CONNECTION_URI, PostgresConfig.USER, PostgresConfig.PASSWORD)
  }
}
