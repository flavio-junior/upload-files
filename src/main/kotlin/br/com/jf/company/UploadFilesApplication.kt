package br.com.jf.company

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UploadFilesApplication

fun main(args: Array<String>) {
	runApplication<UploadFilesApplication>(*args)
}
