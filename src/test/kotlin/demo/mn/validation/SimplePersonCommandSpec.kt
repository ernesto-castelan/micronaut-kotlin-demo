package demo.mn.validation

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import javax.validation.Validation
import kotlin.test.assertEquals

object SimplePersonCommandSpec: Spek({
    describe("Get hibernate validator") {
        val validator = Validation.buildDefaultValidatorFactory().validator

        describe("Validate name"){
            mapOf(null              to 1,
                  ""                to 1,
                  " "               to 1,
                  "a".repeat(256)   to 1,
                  "hello"           to 0)
            .forEach { name, expectedErrorCount ->
                val command = SimplePersonCommand(name = name)
                it("validation of name '$name' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "name").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate email"){
            mapOf(null              to 1,
                  ""                to 1,
                  " "               to 2,
                  "a".repeat(256)   to 2,
                  "hello"           to 1,
                  "abc@xyz.com"     to 0)
            .forEach { email, expectedErrorCount ->
                val command = SimplePersonCommand(email = email)
                it("validation of email '$email' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "email").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate phone"){
            mapOf(null              to 0,
                  ""                to 1,
                  " "               to 2,
                  "a"               to 2,
                  "a".repeat(10)    to 1,
                  "5".repeat(9)     to 1,
                  "5".repeat(11)    to 1,
                  "5".repeat(10)    to 0)
            .forEach { phone, expectedErrorCount ->
                val command = SimplePersonCommand(phone = phone)
                it("validation of phone '$phone' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "phone").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }
    }
})
