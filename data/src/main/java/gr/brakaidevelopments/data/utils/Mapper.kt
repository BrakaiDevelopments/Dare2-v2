package gr.brakaidevelopments.data.utils

import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.domain.models.User


fun User.asUserEntity(): UserEntity {
    return UserEntity(id, username, password, email, birthday, country, leaderBoardId, profileImage, userProfileState)
}

fun UserEntity.asUser(): User {
    return User(
        id,
        username,
        password,
        email,
        birthday,
        country,
        leaderBoardId,
        profileImage,
        userProfileState
    )
}
