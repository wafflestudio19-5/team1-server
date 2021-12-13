package com.wafflestudio.waffleoverflow.domain.question.service

import com.wafflestudio.waffleoverflow.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
)
