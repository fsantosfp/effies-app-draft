package com.effies.draft.adapter.out.api.msg

data class ScheduleData(
    val data: ScheduleResponse
)

data class ScheduleResponse(
    val schedule: ScheduleInfoResponse
)

data class ScheduleInfoResponse(
    val pages: SchedulePagesResponse,
    val events: List<ScheduleEventResponse>
)

data class SchedulePagesResponse(
    val older: String?,
    val newer: String?
)

data class ScheduleEventResponse(
    val startTime: String,
    val match: ScheduleMatchResponse
)

data class ScheduleMatchResponse(
    val id: String
)