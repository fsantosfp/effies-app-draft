package com.effies.draft.adapter.out.api

import com.effies.draft.adapter.out.api.msg.*
import com.effies.draft.application.port.out.LOLApiPort
import com.fasterxml.jackson.databind.ObjectMapper
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

    private fun request(url: String): HttpResponse<String>{
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("x-api-key", apiKey)
            .GET()
            .build()
        return client.send(request, HttpResponse.BodyHandlers.ofString())
    }

}