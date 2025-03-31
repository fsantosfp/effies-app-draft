package com.effies.draft.application.port.out

import com.effies.draft.adapter.out.api.msg.ProTeamsResponse

interface LOLApiPort {

    fun getTeams(): ProTeamsResponse?

}