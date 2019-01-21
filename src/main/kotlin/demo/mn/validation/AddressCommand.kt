package demo.mn.validation

import demo.mn.validation.annotations.NullNotBlank
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class AddressCommand (
    @get:NotBlank
    @get:Size(max = 255)
    var street: String? = null,

    @get:NotBlank
    @get:Size(max = 255)
    var exteriorNumber: String? = null,

    @get:NullNotBlank
    @get:Size(max = 255)
    var interiorNumber: String? = null,

    @get:NotBlank
    @get:Size(min = 5, max = 5)
    @get:Pattern(regexp = "[0-9]*")
    var postalCode: String? = null
)
