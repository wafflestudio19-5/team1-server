package com.wafflestudio.waffleoverflow.domain.tag.service

import com.wafflestudio.waffleoverflow.domain.tag.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository,
)
