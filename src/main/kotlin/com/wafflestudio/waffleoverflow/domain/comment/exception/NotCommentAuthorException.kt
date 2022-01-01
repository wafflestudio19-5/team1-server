package com.wafflestudio.waffleoverflow.domain.comment.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.NotAllowedException

class NotCommentAuthorException(detail: String) :
    NotAllowedException(errorType = ErrorType.NOT_ALLOWED, detail)
