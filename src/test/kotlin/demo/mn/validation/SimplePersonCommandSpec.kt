package demo.mn.validation

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import javax.validation.Validation
import javax.validation.Validator

class SimplePersonCommandSpec : StringSpec() {

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    init {
        "Validation for name is correct" {
            forall(
                row(null,               1),
                row("",                 1),
                row(" ",                1),
                row("a".repeat(256),    1),
                row("hello",            0)
            ) { name, expectedErrorCount ->
                val command = SimplePersonCommand(name = name)
                val errorCount = validator.validateProperty(command, "name").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for email is correct" {
            forall(
                row(null,               1),
                row("",                 1),
                row(" ",                2),
                row("a".repeat(256),    2),
                row("hello",            1),
                row("abc@xyz.com",      0)
            ) { email, expectedErrorCount ->
                val command = SimplePersonCommand(email = email)
                val errorCount = validator.validateProperty(command, "email").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for phone is correct" {
            forall(
                row(null,               0),
                row("",                 1),
                row(" ",                2),
                row("a",                2),
                row("a".repeat(10),     1),
                row("5".repeat(9),      1),
                row("5".repeat(11),     1),
                row("5".repeat(10),     0)
            ) { phone, expectedErrorCount ->
                val command = SimplePersonCommand(phone = phone)
                val errorCount = validator.validateProperty(command, "phone").size
                errorCount shouldBe expectedErrorCount
            }
        }
    }
}
