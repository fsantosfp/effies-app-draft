package com.effies.draft.adapter.`in`.http.controllers

import com.effies.draft.adapter.`in`.http.msg.ResponseMsg
import com.effies.draft.adapter.`in`.http.msg.UserTeamMsg
import com.effies.draft.adapter.`in`.http.utils.Path.TEAM_PATH
import com.effies.draft.adapter.`in`.http.utils.PathParam.USER_ID
import com.effies.draft.adapter.mappers.toDomain
import com.effies.draft.adapter.mappers.toMsg
import com.effies.draft.application.services.UserTeamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TeamController(
    private val service: UserTeamService
) {

    @PostMapping(TEAM_PATH)
    suspend fun create(
        @PathVariable(USER_ID) userId: String,
        @RequestBody teamRequest: UserTeamMsg
    ): ResponseEntity<ResponseMsg<UserTeamMsg>> {

        val userTeam = service.create(teamRequest.toDomain(userId)).toMsg()

        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseMsg(data = userTeam)
        )
    }

    @GetMapping(TEAM_PATH)
    suspend fun getById(
        @PathVariable(USER_ID) userId: String,
    ): ResponseEntity<ResponseMsg<UserTeamMsg>> {

        val userTeam = service.getById(userId).toMsg()

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseMsg(data = userTeam)
        )
    }
}