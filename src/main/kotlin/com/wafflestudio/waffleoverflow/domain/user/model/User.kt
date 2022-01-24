package com.wafflestudio.waffleoverflow.domain.user.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.model.BaseEntity
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.vote.model.Vote
import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "user")
class User(
    @Column(unique = true)
    @field:NotBlank
    val email: String,

    @field:NotBlank
    var username: String,

    @Column
    var password: String? = null,

    @JsonIgnore
    val authorities: String = "User",

    @field:NotBlank
    var accessToken: String,

    var s3Path: String? = null,

    var location: String? = null,

    var userTitle: String? = null,

    @Column(columnDefinition = "LONGTEXT")
    var aboutMe: String? = null,

    var websiteLink: String? = null,

    var githubLink: String? = null,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [])
    var questions: MutableList<Question> = mutableListOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [])
    var answers: MutableList<Answer> = mutableListOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [])
    var votes: MutableList<Vote> = mutableListOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [])
    var comments: MutableList<Comment> = mutableListOf(),
) : BaseEntity()
