package io.bkbn.sourdough.cli

import co.touchlab.kermit.Logger
import io.bkbn.sourdough.persistence.ConnectionManager
import io.bkbn.sourdough.persistence.repository.AuthorRepository
import io.bkbn.sourdough.persistence.repository.BookRepository
import io.github.unredundant.satisfaketion.core.Extensions.letterify
import io.github.unredundant.satisfaketion.core.Extensions.numerify
import io.github.unredundant.satisfaketion.core.Generator
import io.github.unredundant.satisfaketion.generators.en.EnglishName
import kotlin.random.Random
import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import kotlinx.coroutines.runBlocking

@Suppress("MagicNumber")
class SeedDatabaseCommand : Subcommand("seed", "A command to seed your database with beautiful fake data") {

  private val clean by option(
    ArgType.Boolean,
    shortName = "c",
    description = "Indicates that database should be wiped prior to seeding"
  ).default(false)

  private val seed by option(
    ArgType.Int,
    shortName = "s",
    description = "Provides seed value for random generator"
  ).default(1337)

  override fun execute() {
    if (clean) {
      Logger.i("Performing complete wipe of database as requested")
      ConnectionManager.cleanMigrations() // FIXME DO NOT LEAVE THIS IN PRODUCTION SETTING!
    }

    val random = Random(seed)

    // Perform Database Migrations
    ConnectionManager.performMigrations()

    val authors = (0..999).map {
      EnglishName.firstName.generate(random) + " " + EnglishName.lastName.generate(random)
    }.chunked(500)
      .mapIndexed { i, authors ->
        Logger.i("Saving 500 Authors to Database, iteration: $i")
        runBlocking {
          AuthorRepository.createMany(authors)
        }
      }
      .flatten()

    (0..9999).map {
      BookRepository.BookCreate(
        authorId = authors.random(random).id,
        isbn = Generator { "#############".numerify(random) }.generate(random),
        title = Generator { "The Great ????????".letterify(random) }.generate(random),
        price = Generator { "##.##".numerify(random).toFloat() }.generate(random)
      )
    }.chunked(1000)
      .mapIndexed { i, books ->
        Logger.i("Saving 1000 books to Database, iteration: $i")
        runBlocking {
          BookRepository.createMany(books)
        }
      }
      .flatten()
  }
}
