package demo.mn.hello

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object HelloFunctionsSpec: Spek({
    describe("HelloFunctions test suite") {

        describe("Test capitalizeWords") {
            it("works on empty string") {
                assertEquals("",
                             capitalizeWords(""))
            }

            it ("works on space only string") {
                assertEquals("",
                             capitalizeWords("   "))
            }

            it("works with single word") {
                assertEquals("Demo",
                             capitalizeWords("demo"))
            }

            it("works with multiple words") {
                assertEquals("Demo Test Example",
                             capitalizeWords("demo test example"))
            }

            it("works with consecutive spaces") {
                assertEquals("Demo Test",
                             capitalizeWords("demo   test"))
            }
        }
    }
})
