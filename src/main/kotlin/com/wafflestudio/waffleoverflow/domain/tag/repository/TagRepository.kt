package com.wafflestudio.waffleoverflow.domain.tag.repository

import com.wafflestudio.waffleoverflow.domain.tag.model.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long?>
