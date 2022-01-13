package com.wafflestudio.waffleoverflow

import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import com.wafflestudio.waffleoverflow.domain.comment.repository.CommentRepository
import com.wafflestudio.waffleoverflow.domain.question.repository.QuestionRepository
import com.wafflestudio.waffleoverflow.domain.tag.repository.TagRepository
import com.wafflestudio.waffleoverflow.domain.user.repository.UserRepository
import com.wafflestudio.waffleoverflow.domain.vote.repository.VoteRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
    private val commentRepository: CommentRepository,
    private val voteRepository: VoteRepository,
    private val tagRepository: TagRepository,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        // TODO("Not yet implemented")
    }
}
