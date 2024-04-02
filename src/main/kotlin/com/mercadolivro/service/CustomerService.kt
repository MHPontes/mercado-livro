package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {
//    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
//        name?.let {
//            return customers.filter {
//                it.name.contains(
//                    name,
//                    true
//                )
//            }    //filtrando por nome ou parte do nome digitado no parametro da requisicao, o true indica que pode ser informado qualquer letra ou parte do nome
//        }
//        return customers
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel {
//        return customers.filter { it.id == id }.first()
        return customerRepository.findById(id).orElseThrow{ NotFoundException("Customer [${id}] not exists", "ML-0002") }
    }

    fun create(customer: CustomerModel) {
//        val id = if (customers.isEmpty()) {
//            1
//        } else {
//            customers.last().id!!.toInt() + 1
//        }
//        customer.id = id
//        customers.add(CustomerModel(id, customer.name, customer.email))
        customerRepository.save(customer)
    }

    fun update(customer: CustomerModel) {
//        customers.filter { it.id == customer.id }.first().let {
//            it.name = customer.name
//            it.email = customer.email
//        }
        if (!customerRepository.existsById(customer.id!!)) {  //caso ID existir jogaremos exception
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(@PathVariable id: Int) {
//        customers.removeIf { it.id == id }
//        if (!customerRepository.existsById(id)) {     //Se customer nao existe, joga Exception
//            throw Exception()
//        }

        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }
}
