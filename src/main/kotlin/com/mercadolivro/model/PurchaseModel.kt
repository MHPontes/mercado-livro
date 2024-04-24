package com.mercadolivro.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "purchase")
data class PurchaseModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @ManyToOne // um unico customer pode ter uma ou varias compras
    @JoinColumn(name = "customer_id")
    val customer: CustomerModel,

    @ManyToMany //uma compra pode ter varios livros e o livro pode estar em varias compras
    @JoinTable(name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchase_id")], // Define a coluna de junção na tabela "Purchase". A coluna "purchase_id" na tabela "purchase_book" é mapeada para a chave primária da tabela "Purchase".
        inverseJoinColumns = [JoinColumn(name = "book_id")]) // Define a coluna de junção inversa na tabela "Book". A coluna "book_id" na tabela "purchase_book" é mapeada para a chave primária da tabela "Book".
    val books: List<BookModel>,

    @Column
    val nfe: String? = null,

    @Column
    val price: BigDecimal,

    @Column(name = "created_at")
    val createdAt : LocalDateTime

)