package com.wafflestudio.waffleoverflow.domain.user.util

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.UUID

// from https://wave1994.tistory.com/131

@Configuration
class AWSConfiguration {
    @Bean
    fun assetS3Client(
        @Value("\${aws.access-key}") accessKey: String,
        @Value("\${aws.secret-key}") secretKey: String,
        @Value("\${aws.s3.endpoint}") s3Endpoint: String
    ): AmazonS3 {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .withRegion(Regions.AP_NORTHEAST_2).build()
    }
}

@Component
class S3Utils {

    @Value("\${aws.s3.bucket}")
    lateinit var bucket: String

    @Value("\${aws.s3.dir}")
    lateinit var dir: String

    @Autowired
    lateinit var amazonS3: AmazonS3

    @Throws(IOException::class)
    fun upload(multipartFile: MultipartFile): String {
        val fileName = UUID.randomUUID().toString() + "-" + multipartFile.originalFilename
        val objMeta = ObjectMetadata()

        val bytes = IOUtils.toByteArray(multipartFile.inputStream)
        objMeta.contentLength = bytes.size.toLong()

        val byteArrayIs = ByteArrayInputStream(bytes)

        amazonS3.putObject(
            PutObjectRequest(bucket, dir + fileName, byteArrayIs, objMeta)
        )

        return amazonS3.getUrl(bucket, dir + fileName).toString()
    }
}
