package com.effies.draft.adapter.`in`.http.controllers

import com.effies.draft.adapter.`in`.http.dtos.UserTeamRequest
import com.effies.draft.adapter.`in`.http.utils.Path.TEAM_BY_ID_PATH
import com.effies.draft.adapter.`in`.http.utils.Path.TEAM_PATH
import com.effies.draft.adapter.`in`.http.utils.PathParam.TEAM_ID
import com.effies.draft.domains.UserTeam
import com.effies.draft.application.services.UserTeamService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TeamController(
    private val service: UserTeamService
) {

    @PostMapping(TEAM_PATH)
    suspend fun create(
        @RequestBody teamRequest: UserTeamRequest
    ): UserTeamRequest {

        val userTeam: UserTeam = UserTeam(
            userId = teamRequest.userId,
            name = teamRequest.name,
            coachName = teamRequest.coachName,
            acronym = teamRequest.acronym
        )

        val response = service.create(userTeam)

        return UserTeamRequest(
            userId = response.userId,
            teamId = response.teamId,
            name = response.name,
            coachName = response.coachName,
            acronym = response.acronym
        )
    }

    @GetMapping(TEAM_BY_ID_PATH)
    suspend fun getById(
        @PathVariable(TEAM_ID) teamId: String
    ): String {
        return "ok"
    }
}