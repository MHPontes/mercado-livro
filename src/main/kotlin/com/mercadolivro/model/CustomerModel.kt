package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Profile
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column
    var password : String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)   // Indica que "roles" é uma coleção de elementos do tipo "Profile", carregados junto com o cliente (EAGER).
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])   // Mapeia a coleção para a tabela "customer_roles" com uma chave estrangeira "customer_id" referenciando o cliente.
    var roles: Set<Profile> = setOf()           // Inicializa o conjunto como vazio usando "setOf()".
                                                // Set garante que cada perfil seja único e a ordem não importa.
)