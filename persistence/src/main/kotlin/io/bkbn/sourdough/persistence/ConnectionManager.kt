package io.bkbn.sourdough.persistence

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

object ConnectionManager {

  fun activateDatabaseConnection() { database }
  fun cleanMigrations() { flyway.clean() }
  fun performMigrations() { flyway.migrate() }

  private val flyway: Flyway by lazy {
    Flyway.configure().apply {
      cleanDisabled(false)
      dataSource(PostgresConfig.CONNECTION_URI, PostgresConfig.USER, PostgresConfig.PASSWORD)
    }.load() ?: error("Problem Loading Flyway!! Please verify Database Connection / Migration Info")
  }

  private val database: Database by lazy {
    Database.connect(HikariDataSource(HikariConfig().apply {
      jdbcUrl = PostgresConfig.CONNECTION_URI
      username = PostgresConfig.USER
      password = PostgresConfig.PASSWORD
      maximumPoolSize = PostgresConfig.DEFAULT_MAX_POOL_SIZE
      initializationFailTimeout = PostgresConfig.DEFAULT_INIT_FAIL_TIMEOUT
      isAutoCommit = false
      transactionIsolation = "TRANSACTION_REPEATABLE_READ"
      validate()
    }))
  }

}
