package com.wafflestudio.waffleoverflow.domain.question.model

import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "question")
class Question() : BaseTimeEntity()
