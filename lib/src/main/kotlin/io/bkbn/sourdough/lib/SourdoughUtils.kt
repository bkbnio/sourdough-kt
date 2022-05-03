package io.bkbn.sourdough.lib

object SourdoughUtils {

  fun readFile(path: String) = this::class.java.classLoader
      ?.getResource(path)
      ?.readText()
      ?: error("Unable to locate spec 👀")

}
