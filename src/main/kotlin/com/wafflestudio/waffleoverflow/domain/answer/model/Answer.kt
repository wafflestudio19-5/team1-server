package com.wafflestudio.waffleoverflow.domain.answer.model

import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import com.wafflestudio.waffleoverflow.domain.question.model.Question
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

@Entity
@Table(name = "answer")
class Answer(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [])
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [])
    val question: Question,

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var votes: MutableList<Vote> = mutableListOf(),

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var comments: MutableList<Comment> = mutableListOf(),

    @Column(columnDefinition = "LONGTEXT")
    var body: String,

    var voteCount: Int = 0,

    var accepted: Boolean = false,

    var editedAt: LocalDateTime? = LocalDateTime.now(),

) : BaseTimeEntity()
