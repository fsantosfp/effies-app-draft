package com.effies.draft.mappers

import com.effies.draft.adapter.`in`.http.msg.ScoreMsg
import com.effies.draft.adapter.out.persistence.postgres.ScoreEntity
import com.effies.draft.domains.Score

fun Score.toEntity(teamId:String): ScoreEntity{
    return ScoreEntity(
        teamId = teamId,
        totalScore = this.totalScore,
        lastScore = this.lastScore,
        currentScore = this.currentScore
    )
}

fun ScoreEntity.toDomain(): Score {
    return Score(
    totalScore = this.totalScore,
    lastScore = this.lastScore,
    currentScore = this.currentScore
    )
}

fun Score.toMsg(): ScoreMsg {
    return ScoreMsg(
        totalScore = this.totalScore,
        lastScore = this.lastScore,
        currentScore = this.currentScore
    )
}