package io.bkbn.sourdough.api

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan

class PlaceholderTest : DescribeSpec({
  it("can run a test") {
    1 shouldBeGreaterThan 0
  }
})
