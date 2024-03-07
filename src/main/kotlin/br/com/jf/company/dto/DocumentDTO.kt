package br.com.jf.company.dto

import jakarta.persistence.*

@Entity(name = "tb_file")
data class DocumentDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var author: String = "",
    var description: String = "",
    var directory: String = ""
)