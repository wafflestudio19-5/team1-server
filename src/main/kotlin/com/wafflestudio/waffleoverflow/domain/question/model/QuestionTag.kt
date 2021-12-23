package com.wafflestudio.waffleoverflow.domain.question.model

import com.wafflestudio.waffleoverflow.domain.model.BaseEntity
import com.wafflestudio.waffleoverflow.domain.tag.model.Tag
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "question_tags")
class QuestionTag(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val question: Question,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val tag: Tag,
) : BaseEntity()
