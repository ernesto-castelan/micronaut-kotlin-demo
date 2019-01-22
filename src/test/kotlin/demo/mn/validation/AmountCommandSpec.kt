package demo.mn.validation

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import javax.validation.Validation
import javax.validation.Validator

class AmountCommandSpec : StringSpec() {

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    init {
        "Validation for min is correct" {
            forall(
                row(null,   1),
                row(-1,     1),
                row(0   ,   0),
                row(1000,   0),
                row(1001,   1)
            ) { min, expectedErrorCount ->
                val command = AmountCommand(min = min)
                val errorCount = validator.validateProperty(command, "min").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation for max is correct" {
            forall(
                row(null,   1),
                row(-1,     1),
                row(0   ,   0),
                row(1000,   0),
                row(1001,   1)
            ) { max, expectedErrorCount ->
                val command = AmountCommand(max = max)
                val errorCount = validator.validateProperty(command, "max").size
                errorCount shouldBe expectedErrorCount
            }
        }

        "Validation MaxGreaterThanMin is correct" {
            forall(
                row(null, null, 2),
                row(5, 5,       1),
                row(6, 5,       1),
                row(5, 6,       0)
            ) { min, max, expectedErrorCount ->
                val command = AmountCommand(min = min, max = max)
                val errorCount = validator.validate(command).size
                errorCount shouldBe expectedErrorCount
            }
        }
    }
}

