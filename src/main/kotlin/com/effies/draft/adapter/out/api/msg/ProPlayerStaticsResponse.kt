package com.effies.draft.adapter.out.api.msg

data class ProPlayerStaticsResponse (
    val frames: List<StaticsDetailResponse>
)

data class StaticsDetailResponse(
    val participants: List<ProPlayerStaticsDetailResponse>
)

data class ProPlayerStaticsDetailResponse(
    val participantId: Int,
    val totalGoldEarned: Int,
    val killParticipation: Double,
    val championDamageShare: Double,
    val wardsPlaced: Int,
    val wardsDestroyed: Int,
)