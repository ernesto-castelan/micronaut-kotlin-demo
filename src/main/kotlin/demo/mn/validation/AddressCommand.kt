package demo.mn.validation

import demo.mn.validation.annotations.NullNotBlank
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class AddressCommand (
    street: String? = null,
    exteriorNumber: String? = null,
    interiorNumber: String? = null,
    postalCode: String? = null
) {
    @NotBlank
    @Size(max = 255)
    var street: String? = street

    @NotBlank
    @Size(max = 255)
    var exteriorNumber: String? = exteriorNumber

    @NullNotBlank
    @Size(max = 255)
    var interiorNumber: String? = interiorNumber

    @NotBlank
    @Size(min = 5, max = 5)
    @Pattern(regexp = "[0-9]*")
    var postalCode: String? = postalCode
}
