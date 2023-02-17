package io.bkbn.sourdough.persistence

import org.flywaydb.core.Flyway
import org.komapper.r2dbc.R2dbcDatabase

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

  val database: R2dbcDatabase by lazy {
    R2dbcDatabase("r2dbc:postgresql://test_user:test_password@localhost:5432/test_db")
  }

}
