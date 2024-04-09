package com.mercadolivro.controller.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PutBookRequest(
    @field:NotEmpty(message = "Name cannot be empty")
    var name: String?,

    @field:NotNull(message = "Price must be informed")
    var price: BigDecimal?
)
