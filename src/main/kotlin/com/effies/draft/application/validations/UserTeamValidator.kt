package com.effies.draft.application.validations

import com.effies.draft.application.validations.validator.ValidationResult
import com.effies.draft.application.validations.validator.Validator
import com.effies.draft.domains.UserTeam

class UserTeamValidator : Validator(){

    companion object {
        val CharacterSequence = "(.)\\1{3,}".toRegex()
    }
    fun validate(userTeam: UserTeam): ValidationResult {

        if(userTeam.name.isBlank()){
            fail(field = "name", message = "must not be blank")
        }

        if(userTeam.name.length < 5){
            fail(field = "name", message = "invalid size")
        }

        if(CharacterSequence.containsMatchIn(userTeam.name)){
            fail(field = "name", message = "invalid value")
        }

        return getResult()
    }

}