package br.com.jf.company.vo

data class FileVO(
    var id: Long = 0,
    var author: String = "",
    var description: String = "",
    var directory: String = ""
)