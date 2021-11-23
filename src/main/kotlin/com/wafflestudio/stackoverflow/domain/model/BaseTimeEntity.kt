package com.wafflestudio.stackoverflow.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseTimeEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @CreatedDate
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @LastModifiedDate
    var updatedAt: LocalDateTime? = LocalDateTime.now(),
)