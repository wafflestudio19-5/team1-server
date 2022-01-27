package com.wafflestudio.waffleoverflow.domain.comment.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.InvalidRequestException

class TooLongCommentException(detail: String) :
    InvalidRequestException(errorType = ErrorType.BAD_REQUEST, detail)
