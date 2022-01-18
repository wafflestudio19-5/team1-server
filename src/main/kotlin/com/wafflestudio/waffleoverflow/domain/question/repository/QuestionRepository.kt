package com.wafflestudio.waffleoverflow.domain.question.repository

import com.wafflestudio.waffleoverflow.domain.question.model.Question
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long?> {
    fun findQuestionByTitleContaining(str: String?, pageable: Pageable): Page<Question>?
}
