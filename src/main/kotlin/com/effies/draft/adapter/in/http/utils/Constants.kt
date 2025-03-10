package com.effies.draft.adapter.`in`.http.utils

import com.effies.draft.adapter.`in`.http.utils.PathParam.TEAM_ID
import com.effies.draft.adapter.`in`.http.utils.PathParam.USER_ID

object Path {
    const val TEAM_PATH = "user/{$USER_ID}/team"
    const val TEAM_STATS_PATH = "$TEAM_PATH/{$TEAM_ID}/stats"
}

object PathParam {
    const val USER_ID = "user_id"
    const val TEAM_ID = "team_id"
}