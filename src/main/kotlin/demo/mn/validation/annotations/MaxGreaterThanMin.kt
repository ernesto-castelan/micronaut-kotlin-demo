package demo.mn.validation.annotations

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@Target(CLASS, ANNOTATION_CLASS)
@Retention(RUNTIME)
@MustBeDocumented

@Constraint(validatedBy = [MaxGreaterThanMinValidator::class])

annotation class MaxGreaterThanMin (
    val message: String = "{demo.mn.validation.annotations.MaxGreaterThanMin.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
