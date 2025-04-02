package com.effies.draft.mappers.draft

import com.effies.draft.adapter.`in`.http.msg.*
import com.effies.draft.domains.draft.*

fun MarketPlayers.toMsg(): MarketPlayersMsg{
    return MarketPlayersMsg(
        players = this.players.toMsg()
    )
}

fun Collection<ProPlayerMarket>.toMsg(): List<ProPlayerMsg> = this.map { it.toMsg() }

fun ProPlayerMarket.toMsg(): ProPlayerMsg{
    return ProPlayerMsg(
        name = this.name,
        image = this.image,
        role = this.role,
        team = this.team.toMsg(),
        score = this.score.toMsg(),
        value = this.value.toMsg()
    )
}

fun ProPlayerTeam.toMsg(): ProPlayerTeamMsg{
    return ProPlayerTeamMsg(
        name = this.name,
        code = this.code,
        image = this.image,
        homeLeague = this.homeLeague
    )
}

fun ProPlayerScore.toMsg(): ProPlayerScoreMsg{
    return ProPlayerScoreMsg(
        last = this.last,
        average = this.average
    )
}

fun ProPlayerValue.toMsg(): ProPlayerValueMsg{
    return ProPlayerValueMsg(
        price = this.price,
        variation = this.variation
    )
}