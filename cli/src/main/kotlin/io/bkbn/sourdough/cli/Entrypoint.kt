package io.bkbn.sourdough.cli

import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli

@ExperimentalCli
fun main(args: Array<String>) {
  val parser = ArgParser("example")
  val test = TestCommand()
  parser.subcommands(test)
  parser.parse(args)
}
