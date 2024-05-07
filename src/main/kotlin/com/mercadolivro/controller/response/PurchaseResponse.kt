package com.mercadolivro.controller.response

import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseResponse(
    var id: Int? = null,
    var customer: CustomerModel? = null,
    var books: MutableList<BookModel>,
    var nfe: String,
    var price: BigDecimal,
    var createdAt: LocalDateTime
)
