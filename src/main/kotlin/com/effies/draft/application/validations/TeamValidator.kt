package com.effies.draft.application.validations

import com.effies.draft.application.validations.validator.ValidationResult
import com.effies.draft.application.validations.validator.Validator
import com.effies.draft.domains.Team

class TeamValidator : Validator(){

    companion object {
        val CharacterSequence = "(.)\\1{3,}".toRegex()
    }
    fun validate(team: Team): ValidationResult {

        if(team.name.isBlank()){
            fail(field = "name", message = "must not be blank")
        }

        if(team.name.length < 5){
            fail(field = "name", message = "invalid size")
        }

        if(CharacterSequence.containsMatchIn(team.name)){
            fail(field = "name", message = "invalid value")
        }

        return getResult()
    }

}