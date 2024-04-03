package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message = "Name cannot be empty")
    var name: String,

    @field:Email(message = "Invalid email")
    var email: String
)