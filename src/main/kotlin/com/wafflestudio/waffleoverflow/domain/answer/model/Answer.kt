package com.wafflestudio.waffleoverflow.domain.answer.model

import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "answer")
class Answer() : BaseTimeEntity()
