package demo.mn.word

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object WordServiceSpec: Spek({

    describe("Test count") {
        mapOf(""            to 0,
              "  "          to 0,
              "hi"          to 1,
              "one two"     to 2,
              "one   two"   to 2,
              "a b c d"     to 4,
              "a b a b"     to 4,
              "a a a a"     to 4)
        .forEach { text, expectedResult ->
            it("count returns correct result for '$text'") {
                assertEquals(expectedResult,
                             WordService.count(text))
            }
        }
    }

    describe("Test countUnique") {
        mapOf(""            to 0,
              "  "          to 0,
              "hi"          to 1,
              "one two"     to 2,
              "one   two"   to 2,
              "a b c d"     to 4,
              "a b a b"     to 2,
              "a a a a"     to 1)
        .forEach { text, expectedResult ->
            it("countUnique returns correct result for '$text'") {
                assertEquals(expectedResult,
                             WordService.countUnique(text))
            }
        }
    }
})
