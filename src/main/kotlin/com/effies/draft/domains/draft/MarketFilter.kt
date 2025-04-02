package com.effies.draft.domains.draft

data class MarketFilter(
    val byRole: Int? = null,
    val byTeam: String? = null
){
    fun isNotNull(): Boolean = this.byRole != null || !this.byTeam.isNullOrBlank()
}
