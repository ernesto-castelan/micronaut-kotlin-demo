package demo.mn.validation

import demo.mn.validation.annotations.NullNotBlank
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class FullPersonCommand {

    @NotBlank
    @Size(max = 255)
    var firstName: String? = null

    @NullNotBlank
    @Size(max = 255)
    var lastName: String? = null

    @NotNull
    @Valid
    var address: AddressCommand? = null
}
