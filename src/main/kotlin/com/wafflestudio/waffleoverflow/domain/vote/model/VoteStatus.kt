package com.wafflestudio.waffleoverflow.domain.vote.model

enum class VoteStatus {
    UP {
        override fun toString() = "Up"
    },

    NONE {
        override fun toString() = "None"
    },

    DOWN {
        override fun toString() = "Down"
    },
}
