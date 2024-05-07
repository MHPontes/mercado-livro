package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val bookRepository: BookRepository,
    private val applicationEventPublisher: ApplicationEventPublisher   //Injentando para disparar eventos
) {

    // =======================
    // AULA - 74 - DESAFIO DE IMPLEMENTAÇÃO - Fazer com que não seja possivel realizar a compra de um livro que não esteja disponivel.
    // =======================
    fun create(purchaseModel: PurchaseModel) {
        val bookIds = purchaseModel.books.map { it.id }

        // Busca os livros com base nos IDs
        val books = bookRepository.findAllById(bookIds)

        // Encontra os livros inativos e armazena seus IDs
        val inactiveBookIds = books.filter { it.status != BookStatus.ATIVO }.map { it.id }

        if (inactiveBookIds.isNotEmpty()) {
            throw BadRequestException(Errors.ML103.message.format(inactiveBookIds), Errors.ML103.code)
        } else {
            purchaseRepository.save(purchaseModel)
            applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
        }
    }


    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }
}
