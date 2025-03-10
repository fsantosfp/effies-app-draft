package com.effies.draft.adapter.`in`.http.configs

import com.effies.draft.application.exceptions.AlreadyExistsException
import com.effies.draft.application.exceptions.BaseExceptionMessage
import com.effies.draft.application.exceptions.NotFoundException
import com.effies.draft.application.exceptions.ValidatorException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun BadRequestException(exception: BadRequestException): ResponseEntity<BaseExceptionMessage>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            BaseExceptionMessage( error = "BadRequest", exception.message)
        )
    }

    @ExceptionHandler
    fun ValidatorExceptionHandler(exception: ValidatorException): ResponseEntity<BaseExceptionMessage>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            BaseExceptionMessage( error = "ValidationField", exception.error)
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleParsingExceptions(exception: HttpMessageNotReadableException): ResponseEntity<BaseExceptionMessage> {
        val field = (exception.cause as MismatchedInputException).path[0].fieldName

        val error = mapOf(field to listOf("required field"))

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            BaseExceptionMessage(error = "ValidationField", error)
        )
    }

    @ExceptionHandler
    fun NotFoundExceptionHandler(exception: NotFoundException): ResponseEntity<BaseExceptionMessage>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            BaseExceptionMessage( error = "NotFound", exception.error)
        )
    }

    @ExceptionHandler
    fun AlreadyExistsExceptionHandler(exception: AlreadyExistsException): ResponseEntity<BaseExceptionMessage>{
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            BaseExceptionMessage( error = "ResourceAlreadyExists", exception.error)
        )
    }
}