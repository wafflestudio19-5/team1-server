package com.wafflestudio.waffleoverflow.domain.vote.model

import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "vote")
class Vote() : BaseTimeEntity()
