package com.wafflestudio.waffleoverflow.domain.vote.service

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.dto.VoteDto
import com.wafflestudio.waffleoverflow.domain.vote.model.Vote
import com.wafflestudio.waffleoverflow.domain.vote.model.VoteStatus
import com.wafflestudio.waffleoverflow.domain.vote.repository.VoteRepository
import org.springframework.stereotype.Service

@Service
class VoteService(
    private val voteRepository: VoteRepository
) {
    fun addQuestionVote(
        requestBody: VoteDto.Request,
        user: User,
        question: Question,
    ) = addVote(requestBody, user, question, null)

    fun addAnswerVote(
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
}
