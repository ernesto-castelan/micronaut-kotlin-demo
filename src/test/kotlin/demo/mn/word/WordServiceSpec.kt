package demo.mn.word

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class WordServiceSpec : StringSpec({

    "count returns correct results" {
        forall(
            row("",             0),
            row("  ",           0),
            row("hi",           1),
            row("one two",      2),
            row("one   two",    2),
            row("a b c d",      4),
            row("a b a b",      4),
            row("a a a a",      4)
        ) { text, expectedResult ->
            WordService.count(text) shouldBe expectedResult
        }
    }

    "countUnique returns correct results" {
        forall(
            row("",             0),
            row("  ",           0),
            row("hi",           1),
            row("one two",      2),
            row("one   two",    2),
            row("a b c d",      4),
            row("a b a b",      2),
            row("a a a a",      1)
        ) { text, expectedResult ->
            WordService.countUnique(text) shouldBe expectedResult
        }
    }
})
