package br.com.jf.company.controller

import br.com.jf.company.service.UploadImageService
import br.com.jf.company.vo.UrlVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(value = ["/api/upload/file"])
class UploadImageController {

    @Autowired
    private lateinit var uploadFileService: UploadImageService

    @PostMapping(value = ["/image"])
    fun uploadImage(@RequestParam("file") file: MultipartFile): ResponseEntity<UrlVO> {
        val vo: UrlVO = uploadFileService.uploadFile(file)
        return ResponseEntity.ok().body(vo)
    }

    @PutMapping("/{url}")
    fun updateFile(
        @PathVariable("url") url: String,
        @RequestPart(value = "file") file: MultipartFile
    ): UrlVO {
        return uploadFileService.updateFile(url, file)
    }

    @DeleteMapping("/{url}")
    fun deleteObject(@PathVariable("url") url: String): ResponseEntity<*> {
        uploadFileService.deleteFile(url = url)
        return ResponseEntity.noContent().build<Any>()
    }
}
