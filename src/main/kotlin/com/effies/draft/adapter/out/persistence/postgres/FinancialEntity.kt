package com.effies.draft.adapter.out.persistence.postgres

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "financial")
data class FinancialEntity(
    @Id
    val teamId: String,
    val budget: Double = 0.00,
    val patrimony: Double = 0.00,
)
