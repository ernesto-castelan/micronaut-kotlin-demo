package demo.mn.validation

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SimplePersonCommand(

    @get:NotBlank
    @get:Size(max = 255)
    var name: String? = null,

    @get:NotBlank
    @get:Size(max = 255)
    @get:Email
    var email: String? = null,

    @get:Size(min = 10, max = 10)
    @get:Pattern(regexp = "[0-9]*")
    var phone: String? = null
)
