package br.com.jf.company.service

import br.com.jf.company.dto.UriDTO
import br.com.jf.company.utils.ConverterUtils.parseObject
import br.com.jf.company.vo.UriVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.URL

@Service
class UploadImageService {

    @Autowired
    private lateinit var s3Service: S3Service

    fun uploadFile(file: MultipartFile): UriVO {
        val url: URL = s3Service.uploadFile(file = file)
        val urlDTO: UriDTO = parseObject(url, UriDTO::class.java)
        urlDTO.uri = url.toString()
        return parseObject(urlDTO, UriVO::class.java)
    }

    fun deleteFile(url: String) {
        s3Service.deleteFile(url = url)
    }
}