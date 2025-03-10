package com.effies.draft.adapter.out.persistence.postgres

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "score")
data class ScoreEntity (
    @Id
    val teamId: String,
    val totalScore: Double = 0.00,
    val lastScore: Double = 0.00,
    val currentScore: Double = 0.00
)