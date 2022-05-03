package io.bkbn.sourdough.lib

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SourdoughUtilTest : DescribeSpec({
  describe("File Utils") {
    it("can read a file") {
      // act
      val result = SourdoughUtils.readFile("SampleFile.txt")

      // assert
      result shouldBe "HI :)"
    }
  }
})
