package gr.brakaidevelopments.domain.models

import java.util.*

data class Comment(
    val id: UUID = UUID.randomUUID(),
    val user: User,
    val challengeID: UUID,
    val parentID: UUID,
    var message: String,
    var UpVotes: Long = 0,
    var DownVotes: Long = 0,
    var createdAt: Date
)