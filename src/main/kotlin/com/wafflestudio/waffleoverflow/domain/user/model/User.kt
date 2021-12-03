package com.wafflestudio.waffleoverflow.domain.user.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wafflestudio.waffleoverflow.domain.model.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "user")
class User(
    @Column(unique = true)
    @field:NotBlank
    val email: String,

    @field:NotBlank
    var name: String,

    @field:NotBlank
    var password: String,

    @JsonIgnore
    val authorites: String = "User"
) : BaseEntity()
