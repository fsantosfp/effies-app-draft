package com.effies.draft.adapter.`in`.http.exceptions

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
    fun BadRequestException(exception: BadRequestException): ResponseEntity<ExceptionMessage>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionMessage( error = "BadRequest", exception.message)
        )
    }

    @ExceptionHandler
    fun ValidatorExceptionHandler(exception: ValidatorException): ResponseEntity<ExceptionMessage>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionMessage( error = "ValidationField", exception.error)
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleParsingExceptions(exception: HttpMessageNotReadableException): ResponseEntity<ExceptionMessage> {
        val field = (exception.cause as MismatchedInputException).path[0].fieldName

        val error = mapOf(field to listOf("required field"))

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionMessage(error = "ValidationField", error)
        )
    }

    @ExceptionHandler
    fun NotFoundExceptionHandler(exception: NotFoundException): ResponseEntity<ExceptionMessage>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ExceptionMessage( error = "NotFound", exception.error)
        )
    }

    @ExceptionHandler
    fun AlreadyExistsExceptionHandler(exception: AlreadyExistsException): ResponseEntity<ExceptionMessage>{
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            ExceptionMessage( error = "ResourceAlreadyExists", exception.error)
        )
    }
}