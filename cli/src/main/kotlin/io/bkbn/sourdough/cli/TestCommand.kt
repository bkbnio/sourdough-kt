package io.bkbn.sourdough.cli

import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default

@ExperimentalCli
class TestCommand : Subcommand("test", "A command to test the cli") {

  private val flag by option(
    ArgType.Boolean,
    shortName = "f",
    description = "An example of using a flag in the CLI"
  ).default(false)

  override fun execute() {
    when (flag) {
      true -> println("You turned on the flag")
      false -> println("You did NOT turn on the flag 😤")
    }
  }
}
