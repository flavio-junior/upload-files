package br.com.jf.company.service

import br.com.jf.company.dto.UrlDTO
import br.com.jf.company.utils.ConverterUtils.parseObject
import br.com.jf.company.vo.UrlVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.URL

@Service
class UploadImageService {

    @Autowired
    private lateinit var s3Service: S3Service

    fun uploadFile(file: MultipartFile): UrlVO {
        val url: URL = s3Service.processFile(file = file)
        val urlDTO: UrlDTO = parseObject(url, UrlDTO::class.java)
        urlDTO.url = url.file
        return parseObject(urlDTO, UrlVO::class.java)
    }

    fun updateFile(url: String, multipartFile: MultipartFile): UrlVO {
        val url: URL = s3Service.updateFile(url = url, multipartFile = multipartFile)
        val urlDTO: UrlDTO = parseObject(url, UrlDTO::class.java)
        urlDTO.url = url.file
        return parseObject(urlDTO, UrlVO::class.java)
    }

    fun deleteFile(url: String) {
        s3Service.deleteFile(url = url)
    }
}
