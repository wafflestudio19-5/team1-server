package com.wafflestudio.waffleoverflow.domain.question.service

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import com.wafflestudio.waffleoverflow.domain.question.exception.QuestionNotFoundException
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.question.repository.QuestionRepository
import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
) {
    fun findById(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException("Question $id does not exist")
    }

    fun addAnswer(
        requestBody: AnswerDto.Request,
        user: User,
        question: Question
    ): AnswerDto.Response {
        val answer = Answer(user, question, bodyPath = requestBody.body, accepted = false)
        answerRepository.save(answer)
        return AnswerDto.Response(answer)
    }
}
