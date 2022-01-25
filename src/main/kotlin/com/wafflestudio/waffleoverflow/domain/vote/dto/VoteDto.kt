package com.wafflestudio.waffleoverflow.domain.vote.dto

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.vote.model.Vote
import javax.validation.constraints.Pattern

class VoteDto {
    data class Request(
        @Pattern(regexp = "(up|down)")
        val status: String
    )

    data class Response(
        val id: Long,
        val user: UserDto.SimpleResponse,
        val status: String,
        val questionId: Long?,
        val answerId: Long?,
    ) {
        constructor(vote: Vote) : this(
            vote.id,
            UserDto.SimpleResponse(vote.user),
            vote.status.toString(),
            vote.question?.id,
            vote.answer?.id,
        )
    }
}
