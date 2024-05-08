package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message = "Name cannot be empty")
    var name: String,

    @field:Email(message = "Invalid email")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "Password must be informed")
    var password: String
)