package com.effies.draft.application.services

import com.effies.draft.application.port.out.repositories.professional.ProLeagueRepository
import com.effies.draft.application.port.out.repositories.professional.ProTeamRepository
import com.effies.draft.domains.RoleEnum
import com.effies.draft.domains.draft.*
import com.effies.draft.domains.professional.ProTeam
import com.effies.draft.mappers.professional.toDomain

class MarketService(
    private val proTeamRepository: ProTeamRepository,
    private val repository: ProLeagueRepository
) {

    fun getAllMarketPlayers(leagueId: String): MarketPlayers{

        val league = repository.findById(leagueId).orElseThrow().toDomain()
        val teams = proTeamRepository.findAllByLeagueId(leagueId).map { it.toDomain() }

        return fillMarketPLayers(MarketData(teams = teams, league = league))
    }

    fun getFilteredMarketPlayers(leagueId: String, filter: MarketFilter):MarketPlayers{

        val league = repository.findById(leagueId).orElseThrow().toDomain()
        val filteredByRole: MutableList<ProTeam> = mutableListOf()

        val teams = if (filter.byTeam.isNullOrBlank()){
            getAllTeams(leagueId)
        } else {
            filterTeamById(filter.byTeam)
        }

        if(filter.byRole != null){
            teams.forEach { team ->
                filteredByRole.add(team.copy(players = team.players?.filter { it.role == filter.byRole }?.toSet()))
            }
        }else{
            filteredByRole.addAll(teams)
        }

        return fillMarketPLayers(MarketData(teams = filteredByRole, league = league))
    }

    private fun fillMarketPLayers(marketData: MarketData): MarketPlayers {
        val players: MutableList<ProPlayerMarket> = mutableListOf()

        marketData.teams.forEach { team ->

            val teamInfo = ProPlayerTeam(
                name = team.name,
                image = team.image,
                homeLeague = marketData.league.name,
                code = team.code
            )

            team.players!!.forEach { player ->
                val score = ProPlayerScore(
                    last = 3.00,
                    average = 7.00
                )

                val value = ProPlayerValue(
                    price = 10.00,
                    variation = -0.10
                )

                players.add(
                    ProPlayerMarket(
                        name = "${teamInfo.code} ${player.summonerName}",
                        image = player.image,
                        role = RoleEnum.fromId(player.role),
                        score = score,
                        value = value,
                        team = teamInfo
                    )
                )
            }
        }

        return MarketPlayers(players)
    }

    private fun filterTeamById(teamId: String): List<ProTeam>{
        val team = proTeamRepository.findById(teamId).map { it.toDomain() }.orElseThrow()
        return listOf(team)
    }

    private fun getAllTeams(leagueId: String): List<ProTeam>{
        return proTeamRepository.findAllByLeagueId(leagueId).map { it.toDomain() }
    }

    fun validateRole(filter: MarketFilter): Boolean{
        return (filter.byRole != null && RoleEnum.contains(filter.byRole)) || filter.byRole == null
    }
}