package com.wafflestudio.waffleoverflow.domain.question.service

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import com.wafflestudio.waffleoverflow.domain.answer.service.AnswerService
import com.wafflestudio.waffleoverflow.domain.question.dto.QuestionDto
import com.wafflestudio.waffleoverflow.domain.question.exception.AcceptedAnswerExistsException
import com.wafflestudio.waffleoverflow.domain.question.exception.AnswerIsNotUnderQuestionException
import com.wafflestudio.waffleoverflow.domain.question.exception.QuestionNotFoundException
import com.wafflestudio.waffleoverflow.domain.question.exception.UnauthorizedQuestionEditException
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.question.repository.QuestionRepository
import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val answerService: AnswerService,
    private val answerRepository: AnswerRepository,
) {
    fun findById(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException("Question $id does not exist")
    }

    fun addQuestion(
        requestBody: QuestionDto.Request,
        user: User,
    ): Question {
        val question = Question(user, title = requestBody.title, body = requestBody.body)
        questionRepository.save(question)
        return question
    }

    fun editQuestion(
        requestBody: QuestionDto.Request,
        user: User,
        questionId: Long,
    ): Question {
        val question = findById(questionId)
        validateUser(user, question)

        question.title = requestBody.title
        question.body = requestBody.body
        question.editedAt = LocalDateTime.now()

        questionRepository.save(question)
        return question
    }

    fun deleteQuestion(
        user: User,
        question: Question,
    ) {
        validateUser(user, question)
        questionRepository.delete(question)
    }

    fun addAnswer(
        requestBody: AnswerDto.Request,
        user: User,
        questionId: Long,
    ): AnswerDto.Response {
        val question = findById(questionId)
        val answer = Answer(user, question, body = requestBody.body, accepted = false)
        answerRepository.save(answer)
        return AnswerDto.Response(answer)
    }

    fun acceptAnswer(
        user: User,
        questionId: Long,
        answerId: Long,
    ): QuestionDto.Response {
        val question = findById(questionId)
        val answer = answerService.findById(answerId)

        // Check for exceptions
        validateUser(user, question)
        if (!checkAnswerIsUnderQuestion(question, answer)) {
            throw AnswerIsNotUnderQuestionException("Answer does not belong to the question")
        }
        if (!answer.accepted && checkAcceptedAnswerExists(question)) {
            throw AcceptedAnswerExistsException("Accepted answer already exists")
        }

        answer.accepted = !answer.accepted
        return QuestionDto.Response(question)
    }

    private fun validateUser(
        user: User,
        question: Question,
    ) {
        if (user.id != question.user.id) {
            throw UnauthorizedQuestionEditException("User $user.id is not the author of question $question.id")
        }
    }

    private fun checkAcceptedAnswerExists(
        question: Question,
    ): Boolean {
        return question.answers.any { it.accepted }
    }

    private fun checkAnswerIsUnderQuestion(
        question: Question,
        answer: Answer,
    ): Boolean {
        return question.answers.any { it.id == answer.id }
    }
}
