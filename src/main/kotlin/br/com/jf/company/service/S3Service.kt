package br.com.jf.company.service

import br.com.jf.company.execeptions.AWSClientException
import br.com.jf.company.execeptions.AWSServiceException
import br.com.jf.company.execeptions.IllegalArgumentCustomException
import br.com.jf.company.execeptions.ResourceNotFoundException
import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import org.apache.commons.io.FilenameUtils
import org.joda.time.Instant
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.URL

@Service
class S3Service {

    private val logger: Logger = LoggerFactory.getLogger(S3Service::class.java)

    @Autowired
    private lateinit var s3client: AmazonS3

    @Value("\${s3.bucket}")
    private val bucketName: String? = null

    fun uploadFile(file: MultipartFile): URL {
        try {
            val originalName = file.originalFilename
            val extension = FilenameUtils.getExtension(originalName)
            val fileName: String = Instant.now().toDate().time.toString() + "." + extension
            val inputStream: InputStream = file.inputStream
            val contentType: String? = file.contentType
            return uploadFile(inputStream, fileName, contentType)
        } catch (e: AmazonServiceException) {
            throw AWSServiceException(e.message)
        } catch (e: AmazonClientException) {
            throw AWSClientException(e.message)
        } catch (e: IOException) {
            throw IllegalArgumentCustomException(e.message)
        }
    }

    private fun uploadFile(inputStream: InputStream, fileName: String, contentType: String?): URL {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = contentType
        logger.info("Upload start")
        s3client.putObject(bucketName, fileName, inputStream, objectMetadata)
        logger.info("Upload finish")
        return s3client.getUrl(bucketName, fileName)
    }

    fun deleteFile(url: String) {
        try {
            s3client.deleteObject(DeleteObjectRequest(bucketName, url))
        } catch (e: Exception) {
            throw ResourceNotFoundException(exception = e.message)
        }
    }

}