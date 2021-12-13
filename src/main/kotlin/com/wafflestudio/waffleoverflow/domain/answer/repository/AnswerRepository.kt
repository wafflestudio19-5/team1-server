package com.wafflestudio.waffleoverflow.domain.answer.repository

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long?>
