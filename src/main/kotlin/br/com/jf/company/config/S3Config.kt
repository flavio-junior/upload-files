package br.com.jf.company.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {


    @Value("\${aws.access_key_id}")
    private val awsId: String? = null

    @Value("\${aws.secret_access_key}")
    private val awsKey: String? = null

    @Value("\${s3.region}")
    private val region: String? = null

    @Bean
    fun s3client(): AmazonS3 {
        val awsCred = BasicAWSCredentials(awsId, awsKey)
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
            .withCredentials(AWSStaticCredentialsProvider(awsCred)).build()
    }
}