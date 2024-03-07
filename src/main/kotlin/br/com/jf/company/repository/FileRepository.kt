package br.com.jf.company.repository

import br.com.jf.company.dto.DocumentDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FileRepository : JpaRepository<DocumentDTO, Long>