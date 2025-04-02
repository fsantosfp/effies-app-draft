package com.effies.draft.adapter.`in`.http.controllers

import com.effies.draft.adapter.`in`.http.msg.*
import com.effies.draft.adapter.`in`.http.utils.Path.MARKET_PATH
import com.effies.draft.application.exceptions.BadRequestException
import com.effies.draft.application.services.MarketFilter
import com.effies.draft.application.services.MarketService
import com.effies.draft.mappers.draft.toMsg
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MarketController(
    private val market: MarketService
) {

    @GetMapping(MARKET_PATH)
    suspend fun getAllPLayers(
        @RequestHeader("league") league:String,
        @RequestParam(required = false, name = "role") role:Int?,
        @RequestParam(required = false, name = "team") team:String,
    ): ResponseEntity<ResponseMsg<MarketPlayersMsg>> {

        val filter = MarketFilter(role,team)

        if(!market.validateRole(filter)) throw BadRequestException("Invalid Role")

        val marketPlayers = if( filter.isNotNull() ){
             market.getFilteredMarketPlayers(league, filter)
        }else{
            market.getAllMarketPlayers(league)
        }

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMsg(marketPlayers.toMsg()))
    }
}