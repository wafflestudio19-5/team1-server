package com.wafflestudio.waffleoverflow.domain.vote.model

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.user.model.User
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "vote")
class Vote(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val question: Question?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val answer: Answer?,

    // Isn't it constant value?
    @Column
    var status: VoteStatus,
) : BaseTimeEntity()
