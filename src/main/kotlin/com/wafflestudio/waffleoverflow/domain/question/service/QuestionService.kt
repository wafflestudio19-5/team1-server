package com.wafflestudio.waffleoverflow.domain.question.service

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import com.wafflestudio.waffleoverflow.domain.question.dto.QuestionDto
import com.wafflestudio.waffleoverflow.domain.question.exception.QuestionNotFoundException
import com.wafflestudio.waffleoverflow.domain.question.exception.UnauthorizedQuestionEditException
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

    fun validateUser(
        user: User,
        question: Question
    ) {
        if (user.id != question.user.id)
            throw UnauthorizedQuestionEditException("User $user.id is not the author of question $question.id")
    }

    fun editQuestion(
        requestBody: QuestionDto.Request,
        user: User,
        question: Question
    ): Question {
        validateUser(user, question)

        question.title = requestBody.title
        question.body = requestBody.body

        questionRepository.save(question)
        return question
    }

    fun deleteQuestion(
        user: User,
        question: Question
    ) {
        validateUser(user, question)
        questionRepository.delete(question)
    }

    fun addAnswer(
        requestBody: AnswerDto.Request,
        user: User,
        question: Question
    ): AnswerDto.Response {
        val answer = Answer(user, question, body = requestBody.body, accepted = false)
        answerRepository.save(answer)
        return AnswerDto.Response(answer)
    }
}
