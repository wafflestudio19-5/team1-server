package com.wafflestudio.waffleoverflow.domain.question.service

import com.wafflestudio.waffleoverflow.domain.question.exception.QuestionNotFoundException
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.question.repository.QuestionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
) {
    fun findById(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException("Question $id does not exist")
    }
}
