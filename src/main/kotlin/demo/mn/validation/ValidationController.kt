package demo.mn.validation

import com.fasterxml.jackson.core.JsonParseException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Validated
@Controller("/validate")
class ValidationController {

    @Post("/simple")
    fun simple(@Body @NotNull @Valid command: SimplePersonCommand?) {
        println("ok")
    }

    @Post("/full")
    fun full(@Body @NotNull @Valid command: FullPersonCommand?) {
        println("ok")
    }

    @Error
    fun handleValidationErrors(request: HttpRequest<*>, exception: ConstraintViolationException): HttpResponse<*> {
        val errors = exception.constraintViolations.map { violation ->
            Pair(
                violation.propertyPath.drop(2).joinToString("."),
                violation.constraintDescriptor.annotation.annotationClass.simpleName
            )
        }.groupBy({it.first}, {it.second})
        return HttpResponse.badRequest(mapOf("errors" to errors))
    }

    @Error
    fun handleJsonErrors(request: HttpRequest<*>, exception: JsonParseException): HttpResponse<*> {
        val errors = mapOf("." to listOf("InvalidJson"))
        return HttpResponse.badRequest(mapOf("errors" to errors))
    }
}
