package com.wafflestudio.stackoverflow.domain.user.exception

import com.wafflestudio.stackoverflow.global.common.exception.ConflictException
import com.wafflestudio.stackoverflow.global.common.exception.ErrorType

class UserAlreadyExistsException(detail: String=""):
        ConflictException(ErrorType.USER_ALREADY_EXISTS, detail)