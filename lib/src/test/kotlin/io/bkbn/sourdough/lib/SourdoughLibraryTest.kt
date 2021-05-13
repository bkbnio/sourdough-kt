package io.bkbn.sourdough.lib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SourdoughLibraryTest {

  @Test
  fun `Does the thing`() {
    assertEquals("heyheyhey", SourdoughLibrary.coolFeature(3))
  }
}
