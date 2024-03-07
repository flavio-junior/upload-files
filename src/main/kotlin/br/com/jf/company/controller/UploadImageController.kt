package br.com.jf.company.controller

import br.com.jf.company.service.UploadImageService
import br.com.jf.company.vo.UriVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(value = ["/api/upload/file"])
class UploadImageController {

    @Autowired
    private lateinit var uploadFileService: UploadImageService

    @PostMapping(value = ["/image"])
    fun uploadImage(@RequestParam("file") file: MultipartFile): ResponseEntity<UriVO> {
        val vo: UriVO = uploadFileService.uploadFile(file)
        return ResponseEntity.ok().body(vo)
    }

}