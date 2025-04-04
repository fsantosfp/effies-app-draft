package com.effies.draft.adapter.out.scheduler.tasks

import com.effies.draft.adapter.out.api.LegueOfLegendsExternalApi
import com.effies.draft.adapter.out.api.msg.*
import com.effies.draft.adapter.out.persistence.postgres.professional.ProGameEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProGameStatisticsEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProPlayerEntity
import com.effies.draft.adapter.out.scheduler.SchedulerTask
import com.effies.draft.application.port.out.repositories.professional.*
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter

@Component
class GameStaticsTask (
    private val lolClient: LegueOfLegendsExternalApi,
    private val repository: ProGameStatisticsRepository,
    private val playerRepository: ProPlayerRepository,
    private val gameRepository: ProGameRepository,
    private val scheduleRepository: ProScheduleRepository
): SchedulerTask{

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun execute() {

        val startTime = LocalDateTime.of(2025,Month.JANUARY,25, 21,0,0)
        val date = LocalDateTime.of(2025,Month.JANUARY,25, 21,30,0)

        logger.info("Loading statics game $startTime")


        try {

            //val schedule = scheduleRepository.findWithGamesByStartTime(startTime)
            //val game = gameRepository.findStartingGame(startTime, PageRequest.of(0,1)).firstOrNull()
            //val league = leagueRepository.findById(game?.schedule?.league?.id)

            val game = gameRepository
                .findNextGameToPlay(startTime, PageRequest.of(0, 1))
                .firstOrNull()


            if(game != null){

                val statistics = mutableListOf<ProGameStatisticsEntity>()

                val formattedDate = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))

                val gameStatistics = lolClient.getGameStatic(game.id, formattedDate)

                val participantsStatics = lolClient.getIndividualGameStatic(game.id, formattedDate)?.frames

                gameStatistics?.gameMetadata?.blueTeamMetadata?.participantMetadata?.forEach { team ->

                    val commonsStatistics = gameStatistics.frames.map { it.blueTeam }.first()

                    val individualStatics = commonsStatistics.participants
                        .find { it.participantId == team.participantId }!!

                    val extraStatistics = participantsStatics
                        ?.flatMap { it.participants }
                        ?.find { it.participantId == team.participantId }!!

                    val player = playerRepository.findById(team.esportsPlayerId)
                        .orElseThrow { IllegalStateException("Jogador não encontrado") }

                    statistics.add(toEntity(
                        game = game,
                        commonStatistics = commonsStatistics,
                        individualStatics = individualStatics,
                        extraStatistics = extraStatistics,
                        player = player
                        )
                    )

                }

                gameStatistics?.gameMetadata?.redTeamMetadata?.participantMetadata?.forEach { team ->

                    val commonsStatistics = gameStatistics.frames.map { it.redTeam }.first()

                    val individualStatics = commonsStatistics.participants
                        .find { it.participantId == team.participantId }!!

                    val extraStatistics = participantsStatics
                        ?.flatMap { it.participants }
                        ?.find { it.participantId == team.participantId }!!

                    val player = playerRepository.findById(team.esportsPlayerId)
                        .orElseThrow { IllegalStateException("Jogador não encontrado") }

                    statistics.add(toEntity(
                        game = game,
                        commonStatistics = commonsStatistics,
                        individualStatics = individualStatics,
                        extraStatistics = extraStatistics,
                        player = player
                        )
                    )
                }

                if(statistics.isNotEmpty()){
                    repository.saveAll(statistics)
                }
            }

        }catch (e: Exception){
            logger.error("Error when try to load statics of the game", e)
        }
    }

    private fun toEntity(
        game:  ProGameEntity,
        commonStatistics: GameStatistics,
        individualStatics: IndividualStatistics,
        extraStatistics: ProPlayerStaticsDetailResponse,
        player:ProPlayerEntity
        ): ProGameStatisticsEntity {
        return ProGameStatisticsEntity(
            id = "${game.id}${player.id}",
            game = game,
            proPlayer = player,
            towers =  commonStatistics.towers,
            barons = commonStatistics.barons,
            dragons = commonStatistics.dragons.count(),
            inhibitors = commonStatistics.inhibitors,
            kills = individualStatics.kills,
            deaths = individualStatics.deaths,
            assists = individualStatics.assists,
            creepScore = individualStatics.creepScore,
            wardsDestroyed = extraStatistics.wardsDestroyed,
            wardsPlaced = extraStatistics.wardsPlaced,
            totalGoldEarned = extraStatistics.totalGoldEarned,
            killParticipation = extraStatistics.killParticipation,
            championDamageShare = extraStatistics.championDamageShare,
        )
    }

    companion object{
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}