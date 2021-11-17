package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.friends

import kotlinx.serialization.Serializable

@Serializable
data class FriendsResponseDataRecord(
    val _id: String,
    val uuidSender: String,
    val uuidReceiver: String,
    val started: Long
)