package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class PostPurchaseRequest (
    @field:NotNull
    @field:Positive   //so aceita numeros positivos
    @JsonAlias("customer_id")
    val customerId : Int,

    @field:NotNull
    @JsonAlias("book_ids")
    val booksId : Set<Int>  //garante que cada ID de livro seja único
)
