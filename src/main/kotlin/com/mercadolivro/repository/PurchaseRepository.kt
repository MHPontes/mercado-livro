package com.mercadolivro.repository

import com.mercadolivro.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int> {
    // =======================
    // AULA - 74 - DESAFIO DE IMPLEMENTAÇÃO - Fazer com que busque as compras do cliente.
    // =======================
    fun findByCustomerId(customerId: Int): List<PurchaseModel>
}
