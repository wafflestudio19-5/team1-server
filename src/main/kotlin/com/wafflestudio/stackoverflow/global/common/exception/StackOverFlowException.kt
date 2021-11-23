package com.wafflestudio.stackoverflow.global.common.exception

import java.lang.RuntimeException

abstract class StackOverFlowException(val errorType: ErrorType, val detail: String = "") : RuntimeException(errorType.name)

abstract class InvalidRequestException(errorType: ErrorType, detail: String = "") : StackOverFlowException(errorType, detail)
abstract class DataNotFoundException(errorType: ErrorType, detail: String = "") : StackOverFlowException(errorType, detail)
abstract class NotAllowedException(errorType: ErrorType, detail: String = "") : StackOverFlowException(errorType, detail)
abstract class ConflictException(errorType: ErrorType, detail: String = "") : StackOverFlowException(errorType, detail)