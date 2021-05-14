package io.bkbn.sourdough.api

import io.bkbn.sourdough.lib.SourdoughLibrary

private object Config {
  const val myDesiredNumberOfHeys = 5
}

fun main() {
  println(SourdoughLibrary.coolFeature(Config.myDesiredNumberOfHeys))
}
