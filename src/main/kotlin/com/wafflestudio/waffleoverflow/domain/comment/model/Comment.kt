package com.wafflestudio.waffleoverflow.domain.comment.model

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.user.model.User
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "comment")
class Comment(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [])
    val user: User,

    @NotBlank
    var body: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [])
    val question: Question? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [])
    val answer: Answer? = null,
) : BaseTimeEntity()
