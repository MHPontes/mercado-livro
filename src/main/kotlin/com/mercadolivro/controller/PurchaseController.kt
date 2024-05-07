package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.controller.response.PurchaseResponse
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchase")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest) {
        purchaseService.create(purchaseMapper.toModel(request))   // Chama o método 'create' do 'purchaseService', passando como argumento o resultado da conversão do 'request' para um modelo de 'Purchase' através do 'purchaseMapper'.
    }

    // =======================
    // AULA - 74 - DESAFIO DE IMPLEMENTAÇÃO - Fazer com que busque as compras do cliente.
    // =======================
    @GetMapping("/{id}")
    fun getPurchaseByCostumerId(@PathVariable id: Int): List<PurchaseResponse> {
        return purchaseService.getByPurchaseByCustomer(id)
    }

}