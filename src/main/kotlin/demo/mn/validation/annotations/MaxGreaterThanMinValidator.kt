package demo.mn.validation.annotations

import demo.mn.validation.AmountCommand
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class MaxGreaterThanMinValidator : ConstraintValidator<MaxGreaterThanMin, AmountCommand> {

    override fun initialize(constraintAnnotation: MaxGreaterThanMin?) { }

    override fun isValid(command: AmountCommand?, context: ConstraintValidatorContext?): Boolean {
        return if (command == null) true
               else if (command.max == null || command.min == null) true
               else command.max!! > command.min!!
    }
}
