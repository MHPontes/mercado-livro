package com.mercadolivro.controller.request

import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class PostPurchaseRequest (
    @field:NotNull
    @field:Positive   //so aceita numeros positivos
    val customerId : Int,

    @field:NotNull
    val booksId : Set<Int>  //garante que cada ID de livro seja único
)
