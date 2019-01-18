package demo.mn.validation

import demo.mn.validation.annotations.MaxGreaterThanMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@MaxGreaterThanMin
data class AmountCommand (

    @get:NotNull
    @get:Min(0)
    @get:Max(1000)
    var min: Int? = null,

    @get:NotNull
    @get:Min(0)
    @get:Max(1000)
    var max: Int? = null
)
