package com.effies.draft.adapter.`in`.http.exceptions

class ValidatorException(message: Map<String, List<String>>): RuntimeException() {
    val error = message
}

class NotFoundException(message: String): RuntimeException() {
    val error = message
}

class AlreadyExistsException(message: String): RuntimeException(){
    val error = message
}