package com.effies.draft.application.exceptions

class ValidatorException(message: Map<String, List<String>>): RuntimeException() {
    val error = message
}

class NotFoundException(message: String): RuntimeException() {
    val error = message
}

class AlreadyExistsException(message: String): RuntimeException(){
    val error = message
}