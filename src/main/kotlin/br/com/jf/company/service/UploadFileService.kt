package br.com.jf.company.service

import br.com.jf.company.dto.FileDTO
import br.com.jf.company.execeptions.RequiredObjectIsNullException
import br.com.jf.company.execeptions.ResourceNotFoundException
import br.com.jf.company.repository.FileRepository
import br.com.jf.company.utils.ConverterUtils.parseListObjects
import br.com.jf.company.utils.ConverterUtils.parseObject
import br.com.jf.company.vo.FileVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UploadFileService {

    @Autowired
    private lateinit var fileRepository: FileRepository

    @Transactional(readOnly = true)
    fun findAllFiles(): List<FileVO> {
        return parseListObjects(fileRepository.findAll(), FileVO::class.java)
    }

    fun findCategoryById(id: Long): FileVO {
        val entity = fileRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        val company: FileDTO = parseObject(entity, FileDTO::class.java)
        return parseObject(company, FileVO::class.java)
    }

    fun createNewFile(fileVO: FileVO): FileVO {
        val entity: FileDTO = parseObject(fileVO, FileDTO::class.java)
        return parseObject(fileRepository.save(entity), FileVO::class.java)
    }

    fun updateFile(fileVO: FileVO?): FileVO {
        if (fileVO == null) throw RequiredObjectIsNullException()
        val entity = fileRepository.findById(fileVO.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        entity.author = fileVO.author
        entity.description = fileVO.description
        entity.directory = fileVO.directory
        return parseObject(fileRepository.save(entity), FileVO::class.java)
    }

    fun deleteFile(id: Long) {
        val entity = fileRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        fileRepository.delete(entity)
    }
}