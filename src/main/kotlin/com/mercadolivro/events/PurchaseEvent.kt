package com.mercadolivro.events

import com.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,                       //Pode se ler como, alguem comprou alguma coisa
    val purchaseModel: PurchaseModel
) : ApplicationEvent(source)