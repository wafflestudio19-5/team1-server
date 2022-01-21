package com.wafflestudio.waffleoverflow.domain.answer.repository

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long?> {
    fun findAnswersByQuestionIdEquals(questionId: Long, pageable: Pageable): Page<Answer>
}
