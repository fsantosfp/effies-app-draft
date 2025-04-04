package com.effies.draft.adapter.out.api.msg

data class ProGameStatisticsResponse (
    val esportsGameId: String,
    val gameMetadata: ProGameMetaDataResponse,
    val frames: List<FrameStaticsResponse>
)

data class ProGameMetaDataResponse(
    val blueTeamMetadata:ProTeamMetadataResponse,
    val redTeamMetadata:ProTeamMetadataResponse
)

data class ProTeamMetadataResponse(
    val esportsTeamId: String,
    val participantMetadata: List<ProParticipantMetadataResponse>
)

data class ProParticipantMetadataResponse(
    val participantId: Int,
    val esportsPlayerId: String
)

data class FrameStaticsResponse(
    val gameState: String,
    val blueTeam: GameStatistics,
    val redTeam: GameStatistics
)

data class GameStatistics(
    val inhibitors: Int,
    val towers: Int,
    val barons: Int,
    val dragons: List<String>,
    val participants: List<IndividualStatistics>
)

data class IndividualStatistics(
    val participantId: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val creepScore: Int
)