package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.status

import kotlinx.serialization.Serializable

@Serializable
data class StatusResponseDataSession(
    val online: Boolean,
    val gameType: String? = null,
    val mode: String? = null,
    val map: String? = null
)