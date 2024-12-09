package com.effies.draft.adapter.`in`.http.utils

import com.effies.draft.adapter.`in`.http.utils.PathParam.USER_ID

object Path {
    const val TEAM_PATH = "user/{$USER_ID}/team"
}

object PathParam {
    const val USER_ID = "user_id"
}