package com.effies.draft.adapter.mappers

import com.effies.draft.adapter.`in`.http.msg.FinancialMsg
import com.effies.draft.adapter.out.persistence.postgres.FinancialEntity
import com.effies.draft.domains.Financial

fun FinancialMsg.toDomain(): Financial{
    return Financial(
        budget = this.budget,
        patrimony = this.patrimony
    )
}

fun Financial.toMsg(): FinancialMsg{
    return FinancialMsg(
        budget = this.budget,
        patrimony = this.patrimony
    )
}

fun Financial.toEntity(teamId: String): FinancialEntity {
    return FinancialEntity(
        teamId = teamId,
        budget = this.budget,
        patrimony = this.patrimony
    )
}

fun FinancialEntity.toDomain(): Financial {
    return Financial(
        budget = this.budget,
        patrimony = this.patrimony
    )
}