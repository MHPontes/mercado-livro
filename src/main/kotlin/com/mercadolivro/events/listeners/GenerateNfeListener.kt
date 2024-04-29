package com.mercadolivro.events.listeners

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(           //Ira gerar as NFEs
    private val purchaseService: PurchaseService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        val nfe = UUID.randomUUID().toString()   //gerando nfes
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)    //copiando a nfe gerada

        purchaseService.update(purchaseModel) // chamando metodo para salvar a nfe
    }
}