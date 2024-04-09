package com.example.demo.resources

import com.example.demo.dto.ErrorResponse
import jakarta.persistence.EntityNotFoundException
import org.apache.coyote.BadRequestException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleResourceNotFoundException(ex: EntityNotFoundException): ResponseEntity<ErrorResponse> {

        val errorMessage = ErrorResponse(
            title = "Not Found",
            message = ex.message,
            status = HttpStatus.NOT_FOUND.value()
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}