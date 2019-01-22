package demo.mn.hello

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row


class HelloFunctionsSpec : StringSpec({

    "capitalizeWords returns correct results" {
        forall(
            row("",                     ""),
            row("   ",                  ""),
            row("demo",                 "Demo"),
            row("demo test example",    "Demo Test Example"),
            row("demo   test",          "Demo Test")
        ) {input, expectedResult  ->
            capitalizeWords(input) shouldBe expectedResult
        }
    }
})
