package com.wafflestudio.waffleoverflow

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.comment.repository.CommentRepository
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.question.repository.QuestionRepository
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.repository.UserRepository
import com.wafflestudio.waffleoverflow.domain.vote.repository.VoteRepository
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtTokenProvider
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("local")
class DataLoader(
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
    private val commentRepository: CommentRepository,
    private val voteRepository: VoteRepository,
    private val jwtTokenProvider: JwtTokenProvider,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        // Create Users
        val userA = User(
            email = "userA@gmail.com",
            username = "userA",
            accessToken = jwtTokenProvider.generateToken("userA@gmail.com"),
        )

        val userB = User(
            email = "userB@gmail.com",
            username = "userB",
            accessToken = jwtTokenProvider.generateToken("userB@gmail.com"),
        )

        val userC = User(
            email = "userC@gmail.com",
            username = "userC",
            accessToken = jwtTokenProvider.generateToken("userC@gmail.com"),
        )

        val userD = User(
            email = "userD@gmail.com",
            username = "userD",
            accessToken = jwtTokenProvider.generateToken("userD@gmail.com"),
        )

        val userE = User(
            email = "userE@gmail.com",
            username = "userE",
            accessToken = jwtTokenProvider.generateToken("userE@gmail.com"),
        )

        val userF = User(
            email = "userF@gmail.com",
            username = "userF",
            accessToken = jwtTokenProvider.generateToken("userF@gmail.com"),
        )

        val userG = User(
            email = "userG@gmail.com",
            username = "userG",
            accessToken = jwtTokenProvider.generateToken("userG@gmail.com"),
        )

        userRepository.save(userA)
        userRepository.save(userB)
        userRepository.save(userC)
        userRepository.save(userD)
        userRepository.save(userE)
        userRepository.save(userF)
        userRepository.save(userG)

        // Create Question
        val questionA = Question(
            user = userA,
            title = "userA Question",
            body = "Question Test 1 Have a good day! I like Kotlin/Spring boot"
        )

        val questionB = Question(
            user = userB,
            title = "userB Question",
            body = "Question Test 2 Have a good day! I like Kotlin/Spring boot"
        )

        val questionC = Question(
            user = userC,
            title = "userC Question",
            body = "Question Test 3 Have a good day! I like Kotlin/Spring boot"
        )

        questionRepository.save(questionA)
        questionRepository.save(questionB)
        questionRepository.save(questionC)

        // Create Answer
        val answerA = Answer(
            user = userD,
            question = questionA,
            body = "This is an answer for Question A."
        )

        val answerB = Answer(
            user = userE,
            question = questionB,
            body = "This is an answer for Question B."
        )

        val answerC = Answer(
            user = userF,
            question = questionC,
            body = "This is an answer for Question C."
        )

        val answerD = Answer(
            user = userG,
            question = questionA,
            body = "This is an answer for Question A."
        )

        answerRepository.save(answerA)
        answerRepository.save(answerB)
        answerRepository.save(answerC)
        answerRepository.save(answerD)

        // Create Comment
        val commentA = Comment(
            user = userG,
            body = "This is Comment.",
            question = questionA,
        )

        val commentB = Comment(
            user = userF,
            body = "This is Comment.",
            question = questionB,
        )

        val commentC = Comment(
            user = userE,
            body = "This is Comment.",
            question = questionC,
        )

        val commentD = Comment(
            user = userD,
            body = "This is Comment.",
            answer = answerA
        )

        val commentE = Comment(
            user = userF,
            body = "This is Comment.",
            answer = answerA,
        )

        val commentF = Comment(
            user = userA,
            body = "This is Comment.",
            answer = answerC
        )

        commentRepository.save(commentA)
        commentRepository.save(commentB)
        commentRepository.save(commentC)
        commentRepository.save(commentD)
        commentRepository.save(commentE)
        commentRepository.save(commentF)
    }
}
