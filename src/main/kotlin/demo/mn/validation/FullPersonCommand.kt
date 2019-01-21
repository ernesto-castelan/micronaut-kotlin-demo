package demo.mn.validation

import demo.mn.validation.annotations.NullNotBlank
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class FullPersonCommand (

    @get:NotBlank
    @get:Size(max = 255)
    var firstName: String? = null,

    @get:NullNotBlank
    @get:Size(max = 255)
    var lastName: String? = null,

    @get:NotNull
    @get:Valid
    var address: AddressCommand? = null,

    @get:NotNull
    @get:Valid
    var amount: AmountCommand? = null
)
