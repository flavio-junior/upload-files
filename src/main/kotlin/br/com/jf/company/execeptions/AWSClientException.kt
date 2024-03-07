package br.com.jf.company.execeptions

import com.amazonaws.AmazonClientException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class AWSClientException(exception: String?) : AmazonClientException(exception)