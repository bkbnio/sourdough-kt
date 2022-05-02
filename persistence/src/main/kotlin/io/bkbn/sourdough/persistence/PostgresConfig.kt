package io.bkbn.sourdough.persistence

object PostgresConfig {
  private const val PORT: Int = 5432
  private const val HOST: String = "localhost"
  private const val DATABASE: String = "test_db"

  const val DEFAULT_MAX_POOL_SIZE = 5
  const val DEFAULT_INIT_FAIL_TIMEOUT = 60000L

  const val USER: String = "test_user"
  const val PASSWORD: String = "test_password"
  const val CONNECTION_URI = "jdbc:postgresql://$HOST:$PORT/$DATABASE"
}
