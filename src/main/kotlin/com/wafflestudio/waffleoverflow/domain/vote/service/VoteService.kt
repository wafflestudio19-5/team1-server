package com.wafflestudio.waffleoverflow.domain.vote.service

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.answer.service.AnswerService
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.question.service.QuestionService
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.dto.VoteDto
import com.wafflestudio.waffleoverflow.domain.vote.model.Vote
import com.wafflestudio.waffleoverflow.domain.vote.model.VoteStatus
import com.wafflestudio.waffleoverflow.domain.vote.repository.VoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class VoteService(
    private val voteRepository: VoteRepository,
    private val questionService: QuestionService,
    private val answerService: AnswerService
) {
    fun changeQuestionVote(
        requestBody: VoteDto.Request,
        user: User,
        question: Question,
    ): VoteDto.Response {
        if (voteExists(user, question, null)) {
            return updateQuestionVote(requestBody, user, question)
        } else {
            return addQuestionVote(requestBody, user, question)
        }
    }

    fun changeAnswerVote(
        requestBody: VoteDto.Request,
        user: User,
        answer: Answer,
    ): VoteDto.Response {
        if (voteExists(user, null, answer)) {
            return updateAnswerVote(requestBody, user, answer)
        } else {
            return addAnswerVote(requestBody, user, answer)
        }
    }

    private fun voteExists(
        user: User,
        question: Question?,
        answer: Answer?
    ): Boolean {
        if (question != null) {
            return question.votes.any { it.user.id == user.id }
        }
        if (answer != null) {
            return answer.votes.any { it.user.id == user.id }
        }
        return false
    }

    private fun addQuestionVote(
        requestBody: VoteDto.Request,
        user: User,
        question: Question,
    ) = addVote(requestBody, user, question, null)

    private fun addAnswerVote(
        requestBody: VoteDto.Request,
        user: User,
        answer: Answer,
    ) = addVote(requestBody, user, null, answer)

    private fun addVote(
        requestBody: VoteDto.Request,
        user: User,
        question: Question?,
        answer: Answer?,
    ): VoteDto.Response {
        val status = when (requestBody.status) {
            "Up" -> VoteStatus.UP
            "Down" -> VoteStatus.DOWN
            else -> VoteStatus.NONE
        }

        val vote = Vote(user, question, answer, status)
        voteRepository.save(vote)

        return VoteDto.Response(vote)
    }

    private fun updateQuestionVote(
        requestBody: VoteDto.Request,
        user: User,
        question: Question,
    ) = updateVote(requestBody, user, question, null)

    private fun updateAnswerVote(
        requestBody: VoteDto.Request,
        user: User,
        answer: Answer,
    ) = updateVote(requestBody, user, null, answer)

    private fun updateVote(
        requestBody: VoteDto.Request,
        user: User,
        question: Question?,
        answer: Answer?,
    ): VoteDto.Response {
        var status = when (requestBody.status) {
            "Up" -> VoteStatus.UP
            "Down" -> VoteStatus.DOWN
            else -> VoteStatus.NONE
        }

        // find the user's vote
        var vote = Vote(user, question, answer, status)
        if (question != null) {
            vote = question.votes.find { it.user.id == user.id }!!
        }
        if (answer != null) {
            vote = answer.votes.find { it.user.id == user.id }!!
        }

        // check for neutral vote and change status
        status = checkNeutralVote(vote, status)
        vote.status = status

        return VoteDto.Response(vote)
    }

    private fun checkNeutralVote(
        vote: Vote,
        inputStatus: VoteStatus
    ): VoteStatus {
        var outputStatus = inputStatus
        if ((vote.status == VoteStatus.UP && inputStatus == VoteStatus.DOWN) ||
            (vote.status == VoteStatus.DOWN && inputStatus == VoteStatus.UP)
        ) {
            outputStatus = VoteStatus.NONE
        }
        return outputStatus
    }
}
