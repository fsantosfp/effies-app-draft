package com.effies.draft.adapter.`in`.http.exceptions

class ValidatorException(message: Map<String, List<String>>) : RuntimeException() {
    val error = message
}