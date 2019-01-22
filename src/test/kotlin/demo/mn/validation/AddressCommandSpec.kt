package demo.mn.validation

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import javax.validation.Validation
import javax.validation.Validator

class AddressCommandSpec : StringSpec() {

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    init {
        "Validation for street is correct" {
            forall(
                row(null,               1),
                row("",                 1),
                row(" ",                1),
                row("a".repeat(256),    1),
                row("hello",            0)
            ) { street, expectedErrorCount ->
                val command = AddressCommand(street = street)
                val errorCount = validator.validateProperty(command, "street").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for exteriorNumber is correct" {
            forall(
                row(null,               1),
                row("",                 1),
                row(" ",                1),
                row("a".repeat(256),    1),
                row("hello",            0)
            ) { exteriorNumber, expectedErrorCount ->
                val command = AddressCommand(exteriorNumber = exteriorNumber)
                val errorCount = validator.validateProperty(command, "exteriorNumber").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for interiorNumber is correct" {
            forall(
                row(null,               0),
                row("",                 1),
                row(" ",                1),
                row("a".repeat(256),    1),
                row("hello",            0)
            ) { interiorNumber, expectedErrorCount ->
                val command = AddressCommand(interiorNumber = interiorNumber)
                val errorCount = validator.validateProperty(command, "interiorNumber").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for postalCode is correct" {
            forall(
                row(null,           1),
                row("",             2),
                row(" ",            3),
                row(" ".repeat(5),  2),
                row("1".repeat(4),  1),
                row("1".repeat(6),  1),
                row("1".repeat(5),  0),
                row("a".repeat(5),  1)
            ) { postalCode, expectedErrorCount ->
                val command = AddressCommand(postalCode = postalCode)
                val errorCount = validator.validateProperty(command, "postalCode").size
                errorCount shouldBe expectedErrorCount
            }
        }
    }
}
