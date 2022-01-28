package com.wafflestudio.waffleoverflow.domain.answer.service

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.answer.exception.AnswerNotFoundException
import com.wafflestudio.waffleoverflow.domain.answer.exception.UnauthorizedUserException
import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class AnswerService(
    private val answerRepository: AnswerRepository,
) {
    fun findById(id: Long): Answer {
        return answerRepository.findByIdOrNull(id) ?: throw AnswerNotFoundException("Answer $id does not exist")
    }

    fun editAnswer(
        requestBody: AnswerDto.Request,
        user: User,
        answerId: Long,
    ): Answer {
        val answer = findById(answerId)
        validateUser(user, answer)
        answer.body = requestBody.body
        answer.editedAt = LocalDateTime.now()
        return answer
    }

    fun deleteAnswer(
        user: User,
        answerId: Long,
    ) {
        val answer = findById(answerId)
        validateUser(user, answer)
        answerRepository.delete(answer)
    }

    private fun validateUser(
        user: User,
        answer: Answer,
    ) {
        if (user.id != answer.user.id) {
            throw UnauthorizedUserException("User $user.id is not the author of answer $answer.id")
        }
    }
}
