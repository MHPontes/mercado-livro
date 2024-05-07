package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {

    ML001("ML-001", "Invalid request"),
    ML101("ML-101", "Book [%s] not exists"),
    ML102("ML-102", "Cannot update book with status [%s]"),
    ML103("ML-103", "Cannot purchase book [%s] because status is not ATIVO"),
    ML201("ML-201", "Customer [%s] not exists")
}