package com.effies.draft.adapter.`in`.http.utils

import com.effies.draft.adapter.`in`.http.utils.PathParam.TEAM_ID

object Path {
    const val TEAM_PATH = "/team"
    const val TEAM_BY_ID_PATH = "${TEAM_PATH}/${TEAM_ID}"
}

object PathParam {
    const val TEAM_ID = "team_id"
}