package com.wafflestudio.waffleoverflow.domain.comment.model

import com.wafflestudio.waffleoverflow.domain.model.BaseTimeEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "comment")
class Comment() : BaseTimeEntity()
