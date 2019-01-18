package demo.mn.validation.annotations

import org.hibernate.validator.constraints.CompositionType.OR
import org.hibernate.validator.constraints.ConstraintComposition
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Null
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Target(FUNCTION, FIELD, ANNOTATION_CLASS, CONSTRUCTOR, VALUE_PARAMETER)
@Retention(RUNTIME)
@MustBeDocumented

@ConstraintComposition(OR)
@Null
@NotBlank
@ReportAsSingleViolation
@Constraint(validatedBy = [])

annotation class NullNotBlank (
    val message: String = "{demo.mn.validation.annotations.NullOrNotBlank.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
