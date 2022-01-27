package com.wafflestudio.waffleoverflow.domain.comment.exception

import com.wafflestudio.waffleoverflow.global.common.exception.DataNotFoundException
import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType

class CommentNotFoundException(detail: String) :
    DataNotFoundException(errorType = ErrorType.DATA_NOT_FOUND, detail)
