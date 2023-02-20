package io.bkbn.sourdough.persistence

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.komapper.dialect.postgresql.jdbc.PostgreSqlJdbcDialect
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
    val dataSource = HikariDataSource(HikariConfig().apply {
      jdbcUrl = PostgresConfig.CONNECTION_URI
      username = PostgresConfig.USER
      password = PostgresConfig.PASSWORD
      maximumPoolSize = PostgresConfig.DEFAULT_MAX_POOL_SIZE
      initializationFailTimeout = PostgresConfig.DEFAULT_INIT_FAIL_TIMEOUT
      isAutoCommit = false
      transactionIsolation = "TRANSACTION_REPEATABLE_READ"
      validate()
    })
    JdbcDatabase(dataSource = dataSource, dialect = PostgreSqlJdbcDialect())
  }
}
