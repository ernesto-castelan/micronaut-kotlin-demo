package demo.mn.validation

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import javax.validation.Validation
import kotlin.test.assertEquals

object FullPersonCommandSpec:Spek({
    describe("Get hibernate validator") {
        val validator = Validation.buildDefaultValidatorFactory().validator

        describe("Validate firstName"){
            mapOf(null              to 1,
                  ""                to 1,
                  " "               to 1,
                  "a".repeat(256)   to 1,
                  "hello"           to 0)
            .forEach { firstName, expectedErrorCount ->
                val command = FullPersonCommand()
                command.firstName = firstName

                it("validation of firstName '$firstName' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "firstName").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate lastName"){
            mapOf(null              to 0,
                  ""                to 1,
                  " "               to 1,
                  "a".repeat(256)   to 1,
                  "hello"           to 0)
            .forEach { lastName, expectedErrorCount ->
                val command = FullPersonCommand()
                command.lastName = lastName

                it("validation of lastName '$lastName' returns $expectedErrorCount errors") {
                    val errorCount = validator.validateProperty(command, "lastName").size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate address"){
            mapOf(null                                      to 1,
                  AddressCommand()                          to 3,
                  AddressCommand(street = "Test",
                                 exteriorNumber = "123",
                                 interiorNumber = "B",
                                 postalCode = "12345")      to 0)
            .forEach { address, expectedErrorCount ->
                val command = FullPersonCommand()
                command.firstName = "First"
                command.lastName = "Last"
                command.address = address
                command.amount = AmountCommand(min = 5,
                                               max = 10)

                it("validation of address '$address' returns $expectedErrorCount errors") {
                    val errorCount = validator.validate(command).size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }

        describe("Validate amount"){
            mapOf(null                      to 1,
                  AmountCommand()           to 2,
                  AmountCommand(min = 5,
                                max = 10)   to 0)
            .forEach { amount, expectedErrorCount ->
                val command = FullPersonCommand()
                command.firstName = "First"
                command.lastName = "Last"
                command.address = AddressCommand(street = "Test",
                                                 exteriorNumber = "123",
                                                 interiorNumber = "B",
                                                 postalCode = "12345")
                command.amount = amount

                it("validation of amount '$amount' returns $expectedErrorCount errors") {
                    val errorCount = validator.validate(command).size
                    assertEquals(expectedErrorCount, errorCount)
                }
            }
        }
    }
})
