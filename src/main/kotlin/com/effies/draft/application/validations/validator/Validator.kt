package com.effies.draft.application.validations.validator


data class ValidationResult (
    val valid: Boolean,
    val errors: Map<String, List<String>>
)

open class Validator(){

    private var valid: Boolean = true
    private val errors: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun fail( field: String, message: String) {
        valid = false
        if(errors[field].isNullOrEmpty()){
            errors[field] = mutableListOf(message)
        } else{
            errors[field]?.add(message)
        }
    }

    fun getResult() = ValidationResult(valid, errors)

}


