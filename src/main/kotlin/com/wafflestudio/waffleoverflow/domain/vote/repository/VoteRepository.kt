package com.wafflestudio.waffleoverflow.domain.vote.repository

import com.wafflestudio.waffleoverflow.domain.vote.model.Vote
import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository : JpaRepository<Vote, Long?>
