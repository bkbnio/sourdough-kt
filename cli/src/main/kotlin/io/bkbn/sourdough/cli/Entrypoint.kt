package io.bkbn.sourdough.cli

import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli

@ExperimentalCli
fun main(args: Array<String>) {
  val parser = ArgParser("sourdough")
  val seed = SeedDatabaseCommand()
  parser.subcommands(seed)
  parser.parse(args)
}
