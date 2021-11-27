package com.wafflestudio.stackoverflow.global.common.exception

enum class ErrorType(
    val code: Int
) {
    INVALID_REQUEST(0),

    NOT_ALLOWED(3000),

    DATA_NOT_FOUND(4000),

    CONFLICT(9000),
    USER_ALREADY_EXISTS(9001),

    SERVER_ERROR(10000)
}
