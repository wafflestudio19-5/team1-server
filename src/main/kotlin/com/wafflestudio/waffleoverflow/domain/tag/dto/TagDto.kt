package com.wafflestudio.waffleoverflow.domain.tag.dto

import com.wafflestudio.waffleoverflow.domain.tag.model.Tag

class TagDto {
    data class Response(
        val id: Long,
        val name: String,
        val intro: String,
    ) {
        constructor(tag: Tag) : this(
            tag.id,
            tag.name,
            tag.intro,
        )
    }
}
