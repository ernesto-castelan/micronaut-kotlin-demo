package demo.mn.validation

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import javax.validation.Validation
import javax.validation.Validator

class FullPersonCommandSpec : StringSpec() {

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    init {
        "Validation for firstName is correct" {
            forall(
                row(null,               1),
                row("",                 1),
                row(" ",                1),
                row("a".repeat(256),    1),
                row("hello",            0)
            ) { firstName, expectedErrorCount ->
                val command = FullPersonCommand(firstName = firstName)
                val errorCount = validator.validateProperty(command, "firstName").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for lastName is correct" {
            forall(
                row(null,               0),
                row("",                 1),
                row(" ",                1),
                row("a".repeat(256),    1),
                row("hello",            0)
            ) { lastName, expectedErrorCount ->
                val command = FullPersonCommand(lastName = lastName)
                val errorCount = validator.validateProperty(command, "lastName").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for address is correct" {
            forall(
                row(null,                                   1),
                row(AddressCommand(),                       3),
                row(AddressCommand(street = "Test",
                                   exteriorNumber = "123",
                                   interiorNumber = "B",
                                   postalCode = "12345"),   0)
            ) { address, expectedErrorCount ->
                val amount = AmountCommand(min = 5, max = 10)
                val command = FullPersonCommand(firstName = "First",
                                                lastName = "Last",
                                                address = address,
                                                amount = amount)
                val errorCount = validator.validate(command).size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for amount is correct" {
            forall(
                row(null,                       1),
                row(AmountCommand(),            2),
                row(AmountCommand(min = 5,
                                  max = 10),    0)
            ) { amount, expectedErrorCount ->
                val address = AddressCommand(street = "Test",
                                             exteriorNumber = "123",
                                             interiorNumber = "B",
                                             postalCode = "12345")
                val command = FullPersonCommand(firstName = "First",
                                                lastName = "Last",
                                                address = address,
                                                amount = amount)
                val errorCount = validator.validate(command).size
                errorCount shouldBe expectedErrorCount
            }
        }
    }
}
