package io.bkbn.sourdough.api

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan

class PlaceholderIntegrationTest : DescribeSpec({
  it("can run an integration test") {
    1 shouldBeGreaterThan 0
  }
})
