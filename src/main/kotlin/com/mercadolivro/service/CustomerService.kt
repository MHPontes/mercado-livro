package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class CustomerService {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter {
                it.name.contains(
                    name,
                    true
                )
            }    //filtrando por nome ou parte do nome digitado no parametro da requisicao, o true indica que pode ser informado qualquer letra ou parte do nome
        }
        return customers
    }

    fun getCustomers(id: String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun create(customer: PostCustomerRequest) {
        val id = if (customers.isEmpty()) {
            1
        } else {
            customers.last().id.toInt() + 1
        }.toString()

        customers.add(CustomerModel(id, customer.name, customer.email))
    }

    fun update(id: String, customer: PutCustomerRequest) {
        customers.filter { it.id == id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(@PathVariable id: String) {
        customers.removeIf { it.id == id }
    }
}
