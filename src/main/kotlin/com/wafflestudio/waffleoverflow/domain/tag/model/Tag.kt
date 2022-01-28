package com.wafflestudio.waffleoverflow.domain.tag.model

import com.wafflestudio.waffleoverflow.domain.model.BaseEntity
import com.wafflestudio.waffleoverflow.domain.question.model.QuestionTag
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tag")
class Tag(
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    var tagQuestions: MutableList<QuestionTag> = mutableListOf(),

    @NotBlank
    val name: String,

    @NotBlank
    val intro: String,

) : BaseEntity()
