package com.effies.draft.adapter.out.api

import com.effies.draft.adapter.out.api.msg.*
import com.effies.draft.application.port.out.LOLApiPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class LegueOfLegendsExternalApi (
    private val objectMapper: ObjectMapper
): LOLApiPort {

    private val client = HttpClient.newHttpClient()
    private val apiKey = "0TvQnueqKa5mxJntVWt0w4LpLfEkrV1Ta8rQBb9Z"

    override fun getTeams(): ProTeamsResponse? {
        val response = this.request("https://esports-api.lolesports.com/persisted/gw/getTeams?hl=pt-BR")
        return objectMapper.readValue(response.body(), TeamsResponse::class.java).data
    }

    fun getLeague(): ProLeaguesResponse {
        val response = this.request("https://esports-api.lolesports.com/persisted/gw/getLeagues?hl=pt-BR")
        return objectMapper.readValue(response.body(), ProLeagueResponse::class.java).data
    }

    fun getTournaments(leagueId: String): ProLeaguesTournamentResponse{
        val response = this.request("https://esports-api.lolesports.com/persisted/gw/getTournamentsForLeague?hl=pt-BR&leagueId=$leagueId")
        return objectMapper.readValue(response.body(), ProTournamentsResponse::class.java).data
    }

    fun getSchedule(leagueId: String): ScheduleResponse{
        val response = this.request("https://esports-api.lolesports.com/persisted/gw/getSchedule?hl=pt-BR&leagueId=$leagueId")
        return objectMapper.readValue(response.body(), ScheduleData::class.java).data
    }

    fun getSchedule(leagueId: String, page:String): ScheduleResponse{
        val response = this.request("https://esports-api.lolesports.com/persisted/gw/getSchedule?hl=pt-BR&leagueId=$leagueId&pageToken=$page")
        return objectMapper.readValue(response.body(), ScheduleData::class.java).data
    }

    fun getEventsByLeague(leagueId: String,matchId: String): ProEventResponse{
        val response = this.request("$BASE_URL/getEventDetails?hl=pt-BR&id=$matchId&leagueId=$leagueId")
        return objectMapper.readValue(response.body(), ProEventDetailData::class.java).data
    }

    fun getGameStatic(gameId: String, startingTime: String): ProGameStatisticsResponse?{
        val response = this.request("https://feed.lolesports.com/livestats/v1/window/$gameId?startingTime=$startingTime")
        if(response.statusCode() == HttpStatus.NO_CONTENT.value()) return null
        return objectMapper.readValue(response.body(), ProGameStatisticsResponse::class.java)
    }

    fun getIndividualGameStatic(gameId: String, startingTime: String): ProPlayerStaticsResponse?{
        val response = this.request("https://feed.lolesports.com/livestats/v1/details/$gameId?startingTime=$startingTime")
        if(response.statusCode() == HttpStatus.NO_CONTENT.value()) return null
        return objectMapper.readValue(response.body(), ProPlayerStaticsResponse::class.java)
    }

    private fun request(url: String): HttpResponse<String>{
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("x-api-key", apiKey)
            .GET()
            .build()
        return client.send(request, HttpResponse.BodyHandlers.ofString())
    }

    companion object {
        const val BASE_URL = "https://esports-api.lolesports.com/persisted/gw"
    }

}