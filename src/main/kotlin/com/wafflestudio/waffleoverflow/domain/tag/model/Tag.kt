package com.wafflestudio.waffleoverflow.domain.tag.model

import com.wafflestudio.waffleoverflow.domain.model.BaseEntity
import com.wafflestudio.waffleoverflow.domain.question.model.QuestionTag
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tag")
class Tag(
    @Column
    @NotBlank
    val name: String,

    @Column
    val intro: String,

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var tagQuestions: MutableList<QuestionTag> = mutableListOf(),
) : BaseEntity()
