package demo.mn.validation

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import javax.validation.Validation
import kotlin.test.assertEquals

object AddressCommandSpec: Spek({
    describe("Get hibernate validator") {
        val validator = Validation.buildDefaultValidatorFactory().validator

        describe("Validate street"){
            mapOf(null              to 1,
                  ""                to 1,
                  " "               to 1,
                  "a".repeat(256)   to 1,
                  "hello"           to 0)
            .forEach { street, expectedErrorCount ->
                val command = AddressCommand(street = street)
                it("validation of street '$street' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "street").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate exteriorNumber"){
            mapOf(null              to 1,
                  ""                to 1,
                  " "               to 1,
                  "a".repeat(256)   to 1,
                  "hello"           to 0)
            .forEach { exteriorNumber, expectedErrorCount ->
                val command = AddressCommand(exteriorNumber = exteriorNumber)
                it("validation of exteriorNumber '$exteriorNumber' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "exteriorNumber").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate interiorNumber"){
            mapOf(null              to 0,
                  ""                to 1,
                  " "               to 1,
                  "a".repeat(256)   to 1,
                  "hello"           to 0)
            .forEach { interiorNumber, expectedErrorCount ->
                val command = AddressCommand(interiorNumber = interiorNumber)
                it("validation of interiorNumber '$interiorNumber' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "interiorNumber").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate postalCode"){
            mapOf(null              to 1,
                  ""                to 2,
                  " "               to 3,
                  " ".repeat(5)     to 2,
                  "1".repeat(4)     to 1,
                  "1".repeat(6)     to 1,
                  "1".repeat(5)     to 0,
                  "a".repeat(5)     to 1)
            .forEach { postalCode, expectedErrorCount ->
                val command = AddressCommand(postalCode = postalCode)

                it("validation of postalCode '$postalCode' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "postalCode").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }
    }
})
