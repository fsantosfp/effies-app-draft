package com.effies.draft.domains

data class Score(
    val teamId: String,
    val totalScore: Double = 0.00,
    val lastScore: Double = 0.00,
    val currentScore: Double = 0.00
)
