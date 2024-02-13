package br.com.jf.company.controller

import br.com.jf.company.service.UploadFileService
import br.com.jf.company.vo.FileVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping(value = ["/api/upload/fie/author"])
class UploadFileController {

    @Autowired
    private lateinit var uploadFileService: UploadFileService

    @GetMapping
    fun findAllFiles(): List<FileVO> {
        return uploadFileService.findAllFiles()
    }

    @GetMapping(value = ["/{id}"])
    fun findFileById(@PathVariable(value = "id") id: Long): FileVO {
        return uploadFileService.findCategoryById(id)
    }

    @PostMapping
    fun createNewFile(@RequestBody fileVO: FileVO): ResponseEntity<FileVO> {
        val entity: FileVO = uploadFileService.createNewFile(fileVO)
        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(entity.id).toUri()
        return ResponseEntity.created(uri).body(entity)
    }

    @PutMapping
    fun updateFile(@RequestBody fileVO: FileVO): FileVO {
        return uploadFileService.updateFile(fileVO)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteFile(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        uploadFileService.deleteFile(id)
        return ResponseEntity.noContent().build<Any>()
    }
}