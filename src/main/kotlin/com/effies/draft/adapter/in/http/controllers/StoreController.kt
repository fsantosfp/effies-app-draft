package com.effies.draft.adapter.`in`.http.controllers

import com.effies.draft.adapter.`in`.http.utils.Path.STORE_PATH
import org.springframework.web.bind.annotation.GetMapping

class StoreController {

    @GetMapping(STORE_PATH)
    suspend fun getProPlayers(){

    }
}