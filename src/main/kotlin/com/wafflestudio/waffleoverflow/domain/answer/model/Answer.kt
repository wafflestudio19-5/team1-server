package com.wafflestudio.waffleoverflow.domain.answer.model

import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.model.Vote
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val question: Question? = null,

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var votes: MutableList<Vote> = mutableListOf(),

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf(),

    @Column(columnDefinition = "LONGTEXT")
    var bodyPath: String? = null,

    @Column
    val accepted: Boolean
) : BaseTimeEntity()
