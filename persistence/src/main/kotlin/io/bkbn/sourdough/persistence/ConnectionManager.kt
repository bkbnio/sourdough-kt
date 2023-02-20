package io.bkbn.sourdough.persistence

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.flywaydb.core.Flyway
import org.komapper.dialect.postgresql.r2dbc.PostgreSqlR2dbcDialect
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
    val pooledConnectionFactory: ConnectionFactory = ConnectionFactories.get(
      ConnectionFactoryOptions.builder()
        .option(ConnectionFactoryOptions.DRIVER, "pool")
        .option(ConnectionFactoryOptions.PROTOCOL, "postgresql")
        .option(ConnectionFactoryOptions.HOST, "localhost")
        .option(ConnectionFactoryOptions.PORT, 5432)
        .option(ConnectionFactoryOptions.USER, "test_user")
        .option(ConnectionFactoryOptions.PASSWORD, "test_password")
        .option(ConnectionFactoryOptions.DATABASE, "test_db")
        .build()
    )
    R2dbcDatabase(connectionFactory = pooledConnectionFactory, dialect = PostgreSqlR2dbcDialect())
  }
}
