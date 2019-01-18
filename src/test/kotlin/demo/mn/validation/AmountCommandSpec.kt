package demo.mn.validation

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import javax.validation.Validation
import kotlin.test.assertEquals

object AmountCommandSpec: Spek({
    describe("Get hibernate validator") {
        val validator = Validation.buildDefaultValidatorFactory().validator

        describe("Validate min"){
            mapOf(null  to 1,
                  -1    to 1,
                  0     to 0,
                  1000  to 0,
                  1001  to 1)
            .forEach { min, expectedErrorCount ->
                val command = AmountCommand(min = min)
                it("validation of min '$min' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "min").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate max"){
            mapOf(null  to 1,
                  -1    to 1,
                  0     to 0,
                  1000  to 0,
                  1001  to 1)
            .forEach { max, expectedErrorCount ->
                val command = AmountCommand(max = max)
                it("validation of max '$max' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "max").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate max greater than min"){
            mapOf(AmountCommand(null, null) to 2,
                  AmountCommand(5, 5)       to 1,
                  AmountCommand(6, 5)       to 1,
                  AmountCommand(5, 6)       to 0)
            .forEach { command, expectedErrorCount ->
                it("validation of min '${command.min}' and max '${command.max}' returns $expectedErrorCount errors") {
                    val errorCount = validator.validate(command).size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }
    }
})
