package com.effies.draft.adapter.`in`.http.controllers

import com.effies.draft.adapter.`in`.http.msg.ResponseMsg
import com.effies.draft.adapter.`in`.http.msg.TeamMsg
import com.effies.draft.adapter.`in`.http.msg.TeamStatsMsg
import com.effies.draft.adapter.`in`.http.utils.Path.TEAM_PATH
import com.effies.draft.adapter.`in`.http.utils.Path.TEAM_STATS_PATH
import com.effies.draft.adapter.`in`.http.utils.PathParam.TEAM_ID
import com.effies.draft.adapter.`in`.http.utils.PathParam.USER_ID
import com.effies.draft.mappers.toDomain
import com.effies.draft.mappers.toMsg
import com.effies.draft.application.services.TeamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TeamController(
    private val service: TeamService
) {

    @PostMapping(TEAM_PATH)
    suspend fun create(
        @PathVariable(USER_ID) userId: String,
        @RequestBody teamRequest: TeamMsg
    ): ResponseEntity<ResponseMsg<TeamMsg>> {

        val team = service.create(teamRequest.toDomain(userId))

        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseMsg(data = team.toMsg())
        )
    }

    @GetMapping(TEAM_PATH)
    suspend fun getById(
        @PathVariable(USER_ID) userId: String,
    ): ResponseEntity<ResponseMsg<TeamMsg>> {

        val team = service.getById(userId)

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseMsg(data = team.toMsg())
        )
    }

    @GetMapping(TEAM_STATS_PATH)
    suspend fun getStats(
        @PathVariable(USER_ID) userId: String,
        @PathVariable(TEAM_ID) teamId: String
    ): ResponseEntity<ResponseMsg<TeamStatsMsg>>{

        val stats = service.getStats(userId, teamId)

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMsg(stats.toMsg()))
    }
}