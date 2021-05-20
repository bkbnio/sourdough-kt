package io.bkbn.sourdough.lib

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SourdoughLibraryTest : DescribeSpec({
  describe("Sample Test") {
    it("prints a bunch of heys") {
      SourdoughLibrary.coolFeature(3) shouldBe "heyheyhey"
    }
  }
})
