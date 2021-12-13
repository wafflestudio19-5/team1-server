package com.wafflestudio.waffleoverflow.domain.answer.service

import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import org.springframework.stereotype.Service

@Service
class AnswerSerivce(
    private val answerRepository: AnswerRepository,
)
