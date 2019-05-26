package gr.brakaidevelopments.domain.models

import java.util.*

data class LeaderBoardEntry(
    val id: UUID = UUID.randomUUID(),
    var points: Long = 0,
    var num_of_participates: Long = 0,
    var completed_challenges: Long = 0
)