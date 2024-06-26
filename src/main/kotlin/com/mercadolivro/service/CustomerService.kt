package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.enums.Profile
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt : BCryptPasswordEncoder
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
        return customerRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }
    }

    fun create(customer: CustomerModel) {
//        val id = if (customers.isEmpty()) {
//            1
//        } else {
//            customers.last().id!!.toInt() + 1
//        }
//        customer.id = id
//        customers.add(CustomerModel(id, customer.name, customer.email))
        val customerCopy = customer.copy(
            roles = setOf(Profile.CUSTOMER),
            password = bCrypt.encode(customer.password)   //encodificando nosso password
        )
        customerRepository.save(customerCopy)
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

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)   //se email nao existir, retorna true
    }
}
