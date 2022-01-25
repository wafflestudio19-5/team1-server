package com.wafflestudio.waffleoverflow.domain.question.model

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.model.Vote
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "question")
class Question(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    val user: User,

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var answers: MutableList<Answer> = mutableListOf(),

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var votes: MutableList<Vote> = mutableListOf(),

    var voteCount: Int = 0,

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var comments: MutableList<Comment> = mutableListOf(),

    @field:NotBlank
    var title: String,

    @Column(columnDefinition = "LONGTEXT")
    var body: String,

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var questionTags: MutableList<QuestionTag> = mutableListOf(),

    var editedAt: LocalDateTime = LocalDateTime.now(),
) : BaseTimeEntity()
